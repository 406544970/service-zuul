package com.lh;

import com.lh.filter.AccessTokenFilter;
import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class ServiceZuulApplication {

    private static String hystrixUrl = "/hystrix.stream";
    private static String hystrixStreamName = "HystrixMetricsStreamServlet";

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceZuulApplication.class).web(WebApplicationType.SERVLET).run(args);
        System.out.println("http://localhost:2000/swagger-ui.html");
    }

    @Bean
    public AccessTokenFilter accessTokenFilter() {
        return new AccessTokenFilter();
    }

    @Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> getServlet() {
        HystrixMetricsStreamServlet hystrixMetricsStreamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean<HystrixMetricsStreamServlet> servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(hystrixMetricsStreamServlet);
        servletRegistrationBean.addUrlMappings(hystrixUrl);
        servletRegistrationBean.setName(hystrixStreamName);
        return servletRegistrationBean;
    }
}

