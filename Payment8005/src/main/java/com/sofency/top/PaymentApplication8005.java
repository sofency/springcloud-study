package com.sofency.top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author sofency
 * @date 2020/7/16 0:05
 * @package IntelliJ IDEA
 * @description
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class PaymentApplication8005 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8005.class,args);
    }
}
