package com.leyou.search.client;

import com.leyou.item.api.SpecApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-19:02
 */
@FeignClient(value = "item-service")
public interface SpecClient extends SpecApi
{
}
