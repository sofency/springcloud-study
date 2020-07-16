package com.sofency.top.services;

import com.sofency.top.entities.CommonResult;
import com.sofency.top.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author sofency
 * @date 2020/7/16 21:48
 * @package IntelliJ IDEA
 * @description
 */
@Component
@FeignClient(value = "PAYMENT-SERVICE")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    CommonResult<Payment> getPayment(@PathVariable("id") Long id);

    @GetMapping("/payment/get/timeout")
    CommonResult timeout() throws InterruptedException;
}
