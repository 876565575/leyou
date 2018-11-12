package com.leyou.item.service;

import com.leyou.common.enums.ExceptionEnum;
import com.leyou.common.exception.LyException;
import com.leyou.item.entity.Category;
import com.leyou.item.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-16:55
 */

@Service
public class CategoryService
{
	@Autowired
	private CategoryMapper categoryMapper;

	public List<Category> queryCategoryListByPid(Long pid)
	{
		//查询条件，mapper会把对象中的非空属性作为查询条件
		Category category = new Category();
		category.setParentId(pid);
		List<Category> categories = categoryMapper.select(category);
		//判断结果是否为空
		if (CollectionUtils.isEmpty(categories))
		{
			//抛出异常，返回404
			throw new LyException(ExceptionEnum.CATEGORY_NOT_FIND);
		}
		return categories;
	}

	public List<Category> queryByIds(List<Long> ids)
	{
		List<Category> categoryList = categoryMapper.selectByIdList(ids);
		if (CollectionUtils.isEmpty(categoryList))
		{
			//抛出异常，返回404
			throw new LyException(ExceptionEnum.CATEGORY_NOT_FIND);
		}
		return categoryList;
	}

}
