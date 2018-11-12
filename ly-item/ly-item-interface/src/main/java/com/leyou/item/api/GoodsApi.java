package com.leyou.item.api;

import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.Sku;
import com.leyou.item.entity.Spu;
import com.leyou.item.entity.SpuDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-18:50
 */
public interface GoodsApi
{
	/**
	 * 分页查询商品
	 * @param key
	 * @param page
	 * @param rows
	 * @param saleable
	 * @return
	 */
	@GetMapping("/spu/page")
	PageResult<Spu> queryGoodsByPage(
			@RequestParam(value = "key", required = false) String key,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "rows", defaultValue = "5") Integer rows,
			@RequestParam(value = "saleable", required = false) Boolean saleable);

	/**
	 * 根据id查询SpuDetail
	 * @param id
	 * @return
	 */
	@GetMapping("/spu/detail/{id}")
	SpuDetail queryDetailById(@PathVariable("id") Long id);

	/**
	 * 根据SpuId查询Sku
	 * @param id
	 * @return
	 */
	@GetMapping("/sku/list")
	List<Sku> querySkuBySpuId(@RequestParam("id") Long id);
}
