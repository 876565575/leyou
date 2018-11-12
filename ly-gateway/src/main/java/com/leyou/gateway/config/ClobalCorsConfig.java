package com.leyou.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-01-17:48
 */
@Configuration
public class ClobalCorsConfig
{
	@Bean
	public CorsFilter corsFilter()
	{
		//1、允许CORS的配置
		CorsConfiguration config = new CorsConfiguration();
		//1）允许的域，不要写*，否则cookies无法使用
		config.addAllowedOrigin("http://manage.leyou.com");
		config.addAllowedOrigin("http://www.leyou.com");
		//2）是否发送cookie信息
		config.setAllowCredentials(true);
		//3）允许的请求方式
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedMethod(HttpMethod.HEAD);
		config.addAllowedMethod(HttpMethod.OPTIONS);
		config.addAllowedMethod(HttpMethod.PATCH);
		//4）允许的请求头信息
		config.addAllowedHeader("*");
		//5）有效时长
		config.setMaxAge(3600L);

		//2、添加映射路径，拦截一切请求
		UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
		configSource.registerCorsConfiguration("/**", config);

		//3、返回新的CorsFilter
		return new CorsFilter(configSource);

	}
}
