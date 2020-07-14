package com.sofency.top;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author sofency
 * @date 2020/7/14 13:00
 * @package IntelliJ IDEA
 * @description
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerTwoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerTwoApplication.class,args);
    }
}
