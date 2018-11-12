package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-07-18:41
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LySearchApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(LySearchApplication.class);
	}
}
