package com.leyou.item.entity;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-03-15:43
 */

@Data
@Table(name = "tb_spec_param")
public class SpecParam
{
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;
	private Long cid;
	private Long groupId;
	private String name;
	@Column(name = "numerical")
	private Boolean numeric;
	private String unit;
	private Boolean generic;
	private Boolean searching;
	private String segments;
}
