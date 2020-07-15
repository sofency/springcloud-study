package com.sofency.top;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author sofency
 * @date 2020/7/12 18:17
 * @package IntelliJ IDEA
 * @description
 */
@MapperScan("com.sofency.com.sofency.top.dao")
@SpringBootApplication
@EnableEurekaClient //注册到注册中心
public class PaymentApplication8002 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication8002.class, args);

    }
}
