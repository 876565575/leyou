package com.leyou.search.client;

import com.leyou.item.entity.Sku;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-19:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryClientTest
{
	@Autowired
	private CategoryClient categoryClient;

	@Autowired
	private GoodsClient goodsClient;
	@Test
	public void testQueryCategories()
	{
		List<Sku> skus = goodsClient.querySkuBySpuId(2L);
		for (Sku sku : skus) {
			System.out.println("sku.getTitle() = " + sku.getTitle());
			
		}
	}

}