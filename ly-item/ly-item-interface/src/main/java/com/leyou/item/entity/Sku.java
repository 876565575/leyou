package com.leyou.item.entity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name = "tb_sku")
public class Sku
{ 
 	@Id
	@KeySql(useGeneratedKeys = true)
	public Long id ; 
	public Long spuId ;
	public String title ; 
	public String images ; 
	public Long price ; 
	public String indexes ; 
	public String ownSpec ;
	public Boolean enable ; 
	public Date createTime ;
	public Date lastUpdateTime ;
	@Transient
	private Integer stock;
} 
 