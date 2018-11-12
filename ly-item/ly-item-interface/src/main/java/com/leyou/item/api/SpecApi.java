package com.leyou.item.api;

import com.leyou.item.entity.SpecGruop;
import com.leyou.item.entity.SpecParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-18:57
 */
public interface SpecApi
{
	@GetMapping("/spec/groups/{cid}")
	List<SpecGruop> queryGroupByCid(@PathVariable("cid") Long cid);

	@GetMapping("/spec/params")
	List<SpecParam> queryParamsList(@RequestParam(name = "cid", required = false) Long cid,
														   @RequestParam(name = "gid", required = false) Long gid,
														   @RequestParam(name = "searching", required = false) Boolean searching);
}
