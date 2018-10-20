package com.lh.servicezuul;

import com.lh.servicezuul.web.AccessTokenFilter;
import com.lh.servicezuul.web.ErrorExtFilter;
import com.lh.servicezuul.web.ErrorFilter;
import com.lh.servicezuul.web.PositionFilterProcessor;
import com.netflix.zuul.FilterProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ServiceZuulApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ServiceZuulApplication.class).web(true).run(args);
		FilterProcessor.setProcessor(new PositionFilterProcessor());
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}

	@Bean
	public ErrorExtFilter errorExtFilter(){
		return new ErrorExtFilter();
	}
	@Bean
	public AccessTokenFilter accessTokenFilter(){
		return new AccessTokenFilter();
	}
}

