package com.sofency.top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sofency
 * @date 2020/7/15 9:01
 * @package IntelliJ IDEA
 * @description
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentApplication8003 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8003.class,args);
    }
}
