package com.leyou.item.mapper;

import com.leyou.item.entity.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-21:33
 */
public interface BrandMapper extends Mapper<Brand>, IdListMapper<Brand, Long>
{
	@Insert("insert into tb_category_brand (category_id, brand_id) values (#{cid}, #{bid})")
	int insertCategoryBrand(@Param("cid") Long cid, @Param("bid") Long bid);

	@Select("SELECT b.* FROM tb_brand b INNER JOIN tb_category_brand cb ON b.`id` = cb.`brand_id` WHERE cb.`category_id` =  #{cid}")
	List<Brand> selectCategoryBrand(@Param("cid") Long cid);
}
