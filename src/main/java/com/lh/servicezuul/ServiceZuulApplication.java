package com.lh.servicezuul;

import com.lh.servicezuul.filter.AccessTokenFilter;
import com.lh.servicezuul.filter.BlackNameFilter;
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
	}
	@Bean
	public BlackNameFilter blackNameFilter(){
		return new BlackNameFilter();
	}
	@Bean
	public AccessTokenFilter accessTokenFilter(){
		return new AccessTokenFilter();
	}
}

