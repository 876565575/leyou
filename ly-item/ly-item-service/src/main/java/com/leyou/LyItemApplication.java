package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-0:15
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper")
public class LyItemApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(LyItemApplication.class);
	}
}
