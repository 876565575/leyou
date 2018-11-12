package com.leyou.item.mapper;

import com.leyou.item.entity.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-16:46
 */
@RegisterMapper
public interface CategoryMapper extends Mapper<Category>, IdListMapper<Category, Long>
{
}
