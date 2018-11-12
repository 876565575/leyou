package com.leyou.search.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.leyou.common.utils.JsonUtils;
import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.*;
import com.leyou.search.client.BrandClient;
import com.leyou.search.client.CategoryClient;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.client.SpecClient;
import com.leyou.search.elasticsearch.GoodsRepository;
import com.leyou.search.entity.Goods;
import com.leyou.search.entity.SearchRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-20:20
 */
@Service
public class SearchService
{
	@Autowired
	private CategoryClient categoryClient;

	@Autowired
	private BrandClient brandClient;

	@Autowired
	private GoodsClient goodsClient;

	@Autowired
	private SpecClient specClient;

	@Autowired
	private GoodsRepository goodsRepository;

	public Goods buildGoods(Spu spu)
	{
		//查询分类
		List<Category> categoryList = categoryClient.queryCategoryListByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
		List<String> names = categoryList.stream().map(Category::getName).collect(Collectors.toList());
		//查询品牌
		String brand = brandClient.queryBrandById(spu.getBrandId());
		//搜索字段
		String all = spu.getTitle() + StringUtils.join(names, " ") + brand;

		// 所有的sku价格集合
		List<Sku> skus = goodsClient.querySkuBySpuId(spu.getId());
		List<Long> priceList = new ArrayList<>();

		//所有的sku信息
		List<Map<String, String>> skuList = new ArrayList<>();
		for (Sku sku : skus) {
			Map<String, String> map = new HashMap<>();
			map.put("title", sku.getTitle());
			map.put("id", sku.getId().toString());
			map.put("price", sku.getPrice().toString());
			map.put("image", StringUtils.substringBefore(sku.getImages(), ","));
			skuList.add(map);

			priceList.add(sku.getPrice());
		}
		String skuJson = JsonUtils.toJson(skuList);

		//查询所有的可搜索规格参数
		List<SpecParam> specParams = specClient.queryParamsList(spu.getCid3(), null, true);
		//查询商品详情
		SpuDetail spuDetail = goodsClient.queryDetailById(spu.getId());
		//获取通用规格参数
		Map<Long, String> generic = JsonUtils.toMap(spuDetail.getGenericSpec(), Long.class, String.class);
		//获取特有规格参数
		Map<Long, List<String>> special = JsonUtils.nativeRead(spuDetail.getSpecialSpec(), new TypeReference<Map<Long, List<String>>>(){});
		//规格参数
		HashMap<String, Object> specs = new HashMap<>();
		for (SpecParam specParam : specParams) {
			//规格名称
			String key = specParam.getName();
			//规格值
			Object value = "";
			//判断是否为通用规格参数
			if (specParam.getGeneric())
			{
				value = generic.get(specParam.getId());
				if (specParam.getNumeric())
				{
					value = chooseSegment(value.toString(), specParam);
				}
			}else{
				value = special.get(specParam.getId());
			}
			specs.put(key, value);
		}

		//构建goods对象
		Goods goods = new Goods();
		goods.setAll(all); //搜索字段，包含标题，分类，品牌，规格等
		goods.setPrice(priceList); // 所有的sku价格集合
		goods.setSkus(skuJson); // 所有的sku集合 json
		goods.setSpecs(specs); // 所有的可搜索规格信息
		goods.setBrandId(spu.getBrandId());
		goods.setCid1(spu.getCid1());
		goods.setCid2(spu.getCid2());
		goods.setCid3(spu.getCid3());
		goods.setCreateTime(spu.getCreateTime());
		goods.setId(spu.getId());
		goods.setSubTitle(spu.getSubTitle());

		return goods;
	}

	private String chooseSegment(String value, SpecParam p) {
		double val = NumberUtils.toDouble(value);
		String result = "其它";
		// 保存数值段
		for (String segment : p.getSegments().split(",")) {
			String[] segs = segment.split("-");
			// 获取数值范围
			double begin = NumberUtils.toDouble(segs[0]);
			double end = Double.MAX_VALUE;
			if(segs.length == 2){
				end = NumberUtils.toDouble(segs[1]);
			}
			// 判断是否在范围内
			if(val >= begin && val < end){
				if(segs.length == 1){
					result = segs[0] + p.getUnit() + "以上";
				}else if(begin == 0){
					result = segs[1] + p.getUnit() + "以下";
				}else{
					result = segment + p.getUnit();
				}
				break;
			}
		}
		return result;
	}

	public PageResult<Goods> queryGoodsByPage(SearchRequest searchRequest)
	{
		NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
		//结果过滤
		queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id", "subTitle", "skus"}, null));
		//分页
		queryBuilder.withPageable(PageRequest.of(searchRequest.getPage() -1, searchRequest.getRows()));
		//查询条件
		queryBuilder.withQuery(QueryBuilders.matchQuery("all", searchRequest.getKey()));
		//查询
		Page<Goods> goodsPage = goodsRepository.search(queryBuilder.build());
		long total = goodsPage.getTotalElements();
		int totalPages = goodsPage.getTotalPages();
		List<Goods> goodsList = goodsPage.getContent();
		return new PageResult(total, totalPages, goodsList);
	}
}
