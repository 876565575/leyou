package com.leyou.search.web;

import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.Spu;
import com.leyou.search.entity.Goods;
import com.leyou.search.entity.SearchRequest;
import com.leyou.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-08-11:42
 */
@RestController
@RequestMapping("/search")
public class SearchController
{
	@Autowired
	private SearchService searchService;

	@PostMapping("/page")
	public ResponseEntity<PageResult<Goods>> queryGoodsByPage(@RequestBody SearchRequest searchRequest)
	{
		return ResponseEntity.ok(searchService.queryGoodsByPage(searchRequest));
	}
}
