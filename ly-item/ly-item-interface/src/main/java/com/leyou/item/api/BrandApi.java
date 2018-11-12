package com.leyou.item.api;

import com.leyou.item.entity.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-18:55
 */
public interface BrandApi
{
	@GetMapping("/brand/id/{id}")
	String queryBrandById(@PathVariable("id") Long id);
}
