package com.sofency.top.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author sofency
 * @date 2020/7/12 20:48
 * @package IntelliJ IDEA
 * @description
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced  //增加负载均衡机制
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
