package com.leyou.item.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-03-14:57
 */
@Data
@Table(name = "tb_spec_group")
public class SpecGruop
{
	@Id
	@KeySql(useGeneratedKeys = true)
	private Long id;

	private Long cid;

	private String name;
}
