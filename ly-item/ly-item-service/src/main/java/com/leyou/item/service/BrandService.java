package com.leyou.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.Brand;
import com.leyou.item.mapper.BrandMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.persistence.Id;
import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-21:33
 */
@Service
public class BrandService
{
	@Autowired
	private BrandMapper brandMapper;

	public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc)
	{
		//分页
		if (rows == -1)
		{
			rows = 999999999;
		}
		PageHelper.startPage(page, rows);
		//过滤
		Example example = new Example(Brand.class);
		if (StringUtils.isNotBlank(key))
		{
			example.createCriteria().orLike("name", "%"+key+"%")
					.orEqualTo("letter", key.toUpperCase());
		}
		//排序
		if (StringUtils.isNotBlank(sortBy))
		{
			String orderByClause = sortBy + " "+(desc ? "desc" : "asc");
			example.setOrderByClause(orderByClause);
		}
		//查询
		List<Brand> brands = brandMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(brands))
		{
			throw new LyException(ExceptionEnum.BRAND_NOT_FIND);
		}
		PageInfo<Brand> pageInfo = new PageInfo<>(brands);
		return new PageResult<>(pageInfo.getTotal(), brands);
	}

	@Transactional
	public void saveBrand(Brand brand, List<Long> cids)
	{
		//新增品牌
		brand.setId(null);
		int count = brandMapper.insert(brand);
		if (count != 1)
		{
			throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
		}
		//新增中间表
		for (Long cid : cids)
		{
			count = brandMapper.insertCategoryBrand(cid, brand.getId());
			if (count != 1)
			{
				throw new LyException(ExceptionEnum.BRAND_SAVE_ERROR);
			}
		}
	}

	public String queryById(Long id)
	{
		String name = brandMapper.selectByPrimaryKey(id).getName();
		if (name == null)
		{
			throw new LyException(ExceptionEnum.BRAND_NOT_FIND);
		}
		return name;
	}

	public List<Brand> queryBrandByCid(Long cid)
	{
		List<Brand> list = brandMapper.selectCategoryBrand(cid);
		if (CollectionUtils.isEmpty(list))
		{
			throw new LyException(ExceptionEnum.BRAND_NOT_FIND);
		}
		return list;
	}

}
