package com.sofency.top;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author sofency
 * @date 2020/7/12 18:17
 * @package IntelliJ IDEA
 * @description
 */
@MapperScan("com.sofency.top.dao")
@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);

    }
}
