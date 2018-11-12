package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-10-31-23:54
 */

@EnableEurekaServer
@SpringBootApplication
public class LyRegistryApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(LyRegistryApplication.class);
	}
}
