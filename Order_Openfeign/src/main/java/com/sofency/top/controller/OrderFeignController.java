package com.sofency.top.controller;

import com.sofency.top.entities.CommonResult;
import com.sofency.top.entities.Payment;
import com.sofency.top.services.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sofency
 * @date 2020/7/16 22:10
 * @package IntelliJ IDEA
 * @description
 */
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPayment(id);
    }

    @GetMapping("/consumer/payment/get/timeout")
    public CommonResult getPaymentTimeOut() throws InterruptedException {
        return paymentFeignService.timeout();
    }
}
