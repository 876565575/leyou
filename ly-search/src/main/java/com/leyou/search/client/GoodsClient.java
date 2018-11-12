package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-19:00
 */
@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi
{
}
