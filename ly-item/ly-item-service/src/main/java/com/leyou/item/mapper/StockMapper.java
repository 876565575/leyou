package com.leyou.item.mapper;

import com.leyou.item.entity.Stock;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-05-20:22
 */
public interface StockMapper extends Mapper<Stock>, InsertListMapper<Stock>, SelectByIdListMapper<Stock, Long>
{
}
