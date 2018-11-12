package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-21:33
 */

@RestController
@RequestMapping("brand")
public class BrandController
{
	@Autowired
	private BrandService brandService;

	/**
	 *	根据条件分页查询
	 * @param key 		关键字
	 * @param page		当前页
	 * @param rows		每页大小
	 * @param sortBy	排序字段
	 * @param desc		排序方式
	 * @return
	 */
	@GetMapping("page")
	public ResponseEntity<PageResult<Brand>> queryBrandByPage(
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "5") Integer rows,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "desc", defaultValue = "false") Boolean desc
	)
	{
		return ResponseEntity.ok(brandService.queryBrandByPage(key, page, rows, sortBy, desc));
	}

	/**
	 *   新增品牌信息
	 * @param brand
	 * @param cids
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids")List<Long> cids)
	{
		brandService.saveBrand(brand, cids);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping("/cid/{cid}")
	public ResponseEntity<List<Brand>> queryBrandByCid(@PathVariable("cid") Long cid)
	{
		return ResponseEntity.ok(brandService.queryBrandByCid(cid));
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<String> queryBrandById(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(brandService.queryById(id));
	}
}
