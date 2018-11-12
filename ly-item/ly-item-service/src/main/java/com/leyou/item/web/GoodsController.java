package com.leyou.item.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.Sku;
import com.leyou.item.entity.Spu;
import com.leyou.item.entity.SpuDetail;
import com.leyou.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-05-13:36
 */

@RestController
public class GoodsController
{
	@Autowired
	private GoodsService goodsService;

	/**
	 * 分页查询商品
	 * @param key
	 * @param page
	 * @param rows
	 * @param saleable
	 * @return
	 */
	@GetMapping("/spu/page")
	public ResponseEntity<PageResult<Spu>> queryGoodsByPage(
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "5") Integer rows,
			@RequestParam(value = "saleable", required = false) Boolean saleable)
	{

		return ResponseEntity.ok(goodsService.queryGoodsByPage(key, page, rows, saleable));
	}

	/**
	 * 添加商品
	 * @param spu
	 * @return
	 */
	@PostMapping("/goods")
	public ResponseEntity<Void> saveGoods(@RequestBody Spu spu)
	{
		goodsService.saveGoods(spu);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * 根据id查询SpuDetail
	 * @param id
	 * @return
	 */
	@GetMapping("/spu/detail/{id}")
	public ResponseEntity<SpuDetail> queryDetailById(@PathVariable("id") Long id)
	{
		return ResponseEntity.ok(goodsService.queryDetailById(id));
	}

	/**
	 * 根据SpuId查询Sku
	 * @param id
	 * @return
	 */
	@GetMapping("/sku/list")
	public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long id)
	{
		return ResponseEntity.ok(goodsService.querySkuBySpuId(id));
	}

	@PutMapping("/goods")
	public ResponseEntity<Void> updateGoods(@RequestBody Spu spu)
	{
		goodsService.updateGoods(spu);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
