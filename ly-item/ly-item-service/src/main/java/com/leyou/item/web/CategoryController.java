package com.leyou.item.web;

import com.leyou.item.entity.Category;
import com.leyou.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-16:56
 */

@RestController
@RequestMapping("/category")
public class CategoryController
{
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/list")
	public ResponseEntity<List<Category>> queryCategoryListByPid(@RequestParam("pid") Long pid)
	{
		List<Category> categories = categoryService.queryCategoryListByPid(pid);
		return ResponseEntity.ok(categories);
	}

	@GetMapping("/list/ids")
	public ResponseEntity<List<Category>> queryCategoryListByIds(@RequestParam("ids") List<Long> ids)
	{
		return ResponseEntity.ok(categoryService.queryByIds(ids));
	}
}
