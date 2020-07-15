package com.sofency.top.controller;

import cn.hutool.log.Log;
import com.sofency.top.entities.CommonResult;
import com.sofency.top.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
    public static final String PAYMENT_URL="http://PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;


    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment){
        //参数 第一个参数是url  第二个参数是 传入的参数  第三个是 返回的参数
        System.out.println(payment.getSerial());
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        //参数 第一个参数是url  第二个参数是 传入的参数  第三个是 返回的参数
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}
