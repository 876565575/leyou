package com.leyou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-0:07
 */
@EnableZuulProxy
@SpringCloudApplication
public class LyGatewayApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(LyGatewayApplication.class);
	}
}
