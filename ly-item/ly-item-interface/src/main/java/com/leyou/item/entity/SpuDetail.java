package com.leyou.item.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_spu_detail")
public class SpuDetail
{ 
 	@Id
	@KeySql(useGeneratedKeys = true)
	private Long spuId ;
	private String description ;
	private String specialSpec ;
	private String genericSpec ;
	private String packingList ;
	private String afterService ;


} 
 