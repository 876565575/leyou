package com.leyou.search.client;

import com.leyou.item.api.BrandApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-19:01
 */
@FeignClient(value = "item-service")
public interface BrandClient extends BrandApi
{
}
