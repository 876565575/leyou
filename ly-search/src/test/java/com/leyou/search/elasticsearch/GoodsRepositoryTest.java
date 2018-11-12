package com.leyou.search.elasticsearch;

import com.leyou.common.vo.PageResult;
import com.leyou.item.entity.Spu;
import com.leyou.search.client.GoodsClient;
import com.leyou.search.entity.Goods;
import com.leyou.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-20:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsRepositoryTest
{
	@Autowired
	private GoodsRepository goodsRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private GoodsClient goodsClient;

	@Autowired
	private SearchService searchService;
	@Test
	public void test()
	{
	    elasticsearchTemplate.createIndex(Goods.class);
	    elasticsearchTemplate.putMapping(Goods.class);
	}
	
	@Test
	public void testLoad()
	{
		int page = 1;
		int row = 100;
	    //查询spu信息
		while (true) {
			PageResult<Spu> result = goodsClient.queryGoodsByPage(null, page, row, true);
			List<Spu> spuList = result.getItems();
			List<Goods> goodsList = spuList.stream().map(searchService::buildGoods).collect(Collectors.toList());
			goodsRepository.saveAll(goodsList);
			if (spuList.size() < 100)
			{
				break;
			}
			page++;
		}
	}
	
	
}