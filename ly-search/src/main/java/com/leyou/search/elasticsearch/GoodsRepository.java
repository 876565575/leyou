package com.leyou.search.elasticsearch;

import com.leyou.search.entity.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-20:09
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods, Long>
{
}
