package com.sofency.top.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author sofency
 * @date 2020/7/15 9:03
 * @package IntelliJ IDEA
 * @description
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/payment/zk")
    public String paymentZk(){
        return "SpringCloud with zookeeper"+serverPort+"\t"+ UUID.randomUUID().toString();
    }
}
