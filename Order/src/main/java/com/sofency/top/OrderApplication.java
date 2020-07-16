package com.sofency.top;

import com.sofency.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @author sofency
 * @date 2020/7/12 20:31
 * @package IntelliJ IDEA
 * @description
 */
@SpringBootApplication
@EnableEurekaClient
//@RibbonClient(name = "PAYMENT-SERVICE",configuration= MySelfRule.class)
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }
}
