package com.sofency.top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author sofency
 * @date 2020/7/17 22:29
 * @package IntelliJ IDEA
 * @description  order
 */
@EnableFeignClients
@EnableHystrix
//@EnableCircuitBreaker
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class OrderHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixApplication.class,args);
    }
}
