package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.*;
import com.leyou.item.mapper.*;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.xml.soap.Detail;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-05-13:35
 */
@Service
public class GoodsService
{
	@Autowired
	private SpuMapper spuMapper;

	@Autowired
	private SpuDetailMapper spuDetailMapper;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private SkuMapper skuMapper;

	@Autowired
	private StockMapper stockMapper;

	public PageResult<Spu> queryGoodsByPage(String key, Integer page, Integer rows, Boolean saleable)
	{
		//分页
		if (rows == -1)
		{
			rows = 999999999;
		}
		PageHelper.startPage(page, rows);

		//过滤
		Example example = new Example(Spu.class);
		Example.Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(key))
		{
			criteria.andLike("title", "%"+ key +"%");
		}
		if (saleable != null)
		{
			criteria.andEqualTo("saleable", saleable);
		}
		//默认排序
		example.setOrderByClause("last_update_time DESC");

		//查询
		List<Spu> spus = spuMapper.selectByExample(example);

		//判断查询结果是否为空
		if (CollectionUtils.isEmpty(spus))
		{
			throw new LyException(ExceptionEnum.GOODS_NOT_FIND);
		}

		//解析分类和品牌
		loadCategoryAndBrand(spus);

		//解析分页结果
		PageInfo<Spu> info = new PageInfo<>(spus);

		return new PageResult<Spu>(info.getTotal(), spus);
	}

	private void loadCategoryAndBrand(List<Spu> spus)
	{
		for (Spu spu : spus)
		{
			//处理商品分类
			List<String> names = categoryService.queryByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()))
					.stream().map(Category::getName).collect(Collectors.toList());
			spu.setCname(StringUtils.join(names, "/"));
			//处理商品名称
			spu.setBname(brandService.queryById(spu.getBrandId()));

		}
	}

	@Transactional
	public void saveGoods(Spu spu)
	{
		//新增spu
		spu.setId(null);
		spu.setCreateTime(new Date());
		spu.setLastUpdateTime(spu.getCreateTime());
		spu.setSaleable(true);
		spu.setValid(false);
		int count = spuMapper.insert(spu);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.GOODS_SAVE_FAILED);
		}
		//新增detail
		SpuDetail detail = spu.getSpuDetail();
		detail.setSpuId(spu.getId());
		count = spuDetailMapper.insert(detail);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.GOODS_SAVE_FAILED);
		}
		//新增sku
		List<Sku> skus = spu.getSkus();
		List<Stock> stockList = new ArrayList<>();
		for (Sku sku : skus)
		{
			sku.setId(null);
			sku.setSpuId(spu.getId());
			sku.setCreateTime(new Date());
			sku.setLastUpdateTime(sku.getCreateTime());
			count = skuMapper.insert(sku);
			if (count != 1)
			{
				throw new LyException(ExceptionEnum.GOODS_SAVE_FAILED);
			}
			//新增库存
			Stock stock = new Stock();
			stock.setStock(sku.getStock());
			stock.setSkuId(sku.getId());
			stockList.add(stock);
		}
		count = stockMapper.insertList(stockList);
		if (count != stockList.size())
		{
			throw new LyException(ExceptionEnum.GOODS_SAVE_FAILED);
		}
	}

	public SpuDetail queryDetailById(Long id)
	{
		SpuDetail spuDetail = spuDetailMapper.selectByPrimaryKey(id);
		if (spuDetail == null)
		{
			throw new LyException(ExceptionEnum.GOODS_DETAIL_NOT_FIND);
		}
		return spuDetail;
	}

	public List<Sku> querySkuBySpuId(Long id)
	{
		//查询SKU
		Sku sku = new Sku();
		sku.setSpuId(id);
		List<Sku> skuList = skuMapper.select(sku);
		if (CollectionUtils.isEmpty(skuList))
		{
			throw new LyException(ExceptionEnum.GOODS_SKU_NOT_FIND);
		}
		// 查询库存
		List<Long> ids = skuList.stream().map(Sku::getId).collect(Collectors.toList());
		List<Stock> stockList = stockMapper.selectByIdList(ids);
		if (CollectionUtils.isEmpty(stockList))
		{
			throw new LyException(ExceptionEnum.GOODS_STOCK_NOT_FIND);
		}
		Map<Long, Integer> stockMap = stockList.stream().collect(Collectors.toMap(Stock::getSkuId, Stock::getStock));
		skuList.forEach(sku1 -> sku1.setStock(stockMap.get(sku1.getId())));
		return skuList;
	}

	@Transactional
	public void updateGoods(Spu spu)
	{
		//修改spu
		spu.setLastUpdateTime(new Date());
		int count = spuMapper.updateByPrimaryKey(spu);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.GOODS_SPU_PUDATE_FAILED);
		}
		//修改detail
		SpuDetail spuDetail = spu.getSpuDetail();
		count = spuDetailMapper.updateByPrimaryKey(spuDetail);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.GOODS_DETAIL_PUDATE_FAILED);
		}
		//修改sku
		//删除原有sku重新添加
		Example example = new Example(Sku.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("spuId", spu.getId());
		int i = skuMapper.selectCountByExample(example);
		count = skuMapper.deleteByExample(example);
		if (i != count)
		{
			throw new LyException(ExceptionEnum.GOODS_SKU_DELETE_FAILED);
		}
		ArrayList<Stock> stocks = new ArrayList<>();
		List<Sku> skus = spu.getSkus();
		for (Sku sku : skus) {
			sku.setSpuId(spu.getId());
			sku.setId(null);
			sku.setCreateTime(spu.getCreateTime());
			sku.setLastUpdateTime(new Date());
			count = skuMapper.insert(sku);
			if (count != 1)
			{
				throw new LyException(ExceptionEnum.GOODS_SKU_SAVE_FAILED);
			}

			Stock stock = new Stock();
			stock.setSkuId(sku.getId());
			stock.setStock(sku.getStock());
			stocks.add(stock);
		}

		count = stockMapper.insertList(stocks);
		if (count != stocks.size())
		{
			throw new LyException(ExceptionEnum.GOODS_STOCK_ADD_FAILED);
		}

	}
}
