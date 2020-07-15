package com.sofency.top.controller;

import com.sofency.top.entities.CommonResult;
import com.sofency.top.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author sofency
 * @date 2020/7/12 20:34
 * @package IntelliJ IDEA
 * @description
 */
@RestController
@Slf4j
public class OrderController {
    //服方
    public static final String INVOKE_URL="http://Payment-service";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/zk")
    public String paymentInfo(){
        //参数 第一个参数是url  第二个参数是 传入的参数  第三个是 返回的参数
        return restTemplate.getForObject(INVOKE_URL+"/payment/zk/",String.class);
    }
}
