package com.leyou.item.web;

import com.leyou.item.entity.SpecGruop;
import com.leyou.item.entity.SpecParam;
import com.leyou.item.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-03-15:00
 */

@RestController
@RequestMapping("/spec")
public class SpecController
{
	@Autowired
	private SpecService specService;

	@GetMapping("/groups/{cid}")
	public ResponseEntity<List<SpecGruop>> queryGroupByCid(@PathVariable("cid") Long cid)
	{
		return ResponseEntity.ok(specService.queryGroupByCid(cid));
	}

	@PostMapping("/group")
	public ResponseEntity<Void> saveGroup(@RequestBody SpecGruop specGruop)
	{
		specService.saveGroup(specGruop);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/group")
	public ResponseEntity<Void> modifyGroup(@RequestBody SpecGruop group)
	{
		specService.modifyGroup(group);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/group/{id}")
	public ResponseEntity<Void> removeGroup(@PathVariable("id") Long id)
	{
		specService.removeGroup(id);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}


	@PostMapping("/param")
	public ResponseEntity<Void> saveParam(@RequestBody SpecParam specParam)
	{
		specService.saveParam(specParam);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/param")
	public ResponseEntity<Void> modifyParam(@RequestBody SpecParam specParam)
	{
		specService.modifyParam(specParam);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@DeleteMapping("/param/{pid}")
	public ResponseEntity<Void> removeParam(@PathVariable("pid") Long pid)
	{
		specService.removeParam(pid);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/params")
	public ResponseEntity<List<SpecParam>> queryParamsList(@RequestParam(name = "cid", required = false) Long cid,
														   @RequestParam(name = "gid", required = false) Long gid,
														   @RequestParam(name = "searching", required = false) Boolean searching)
	{
		return ResponseEntity.ok(specService.queryParamsList(cid, gid, searching));
	}
}
