package com.leyou.item.api;

import com.leyou.item.entity.Category;
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
public interface CategoryApi
{
	@GetMapping("/category/list/ids")
    List<Category> queryCategoryListByIds(@RequestParam("ids") List<Long> ids);
}
