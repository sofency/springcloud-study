package com.sofency.top.controller;

import com.sofency.top.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sofency
 * @date 2020/7/17 21:52
 * @package IntelliJ IDEA
 * @description
 */
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo(@PathVariable("id") Integer id){
       String message = paymentService.paymentInfo(id);
       log.info("**************result"+message);
       return message;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        String message = paymentService.paymentInfoTimeout(id);
        log.info("**************result"+message);
        return message;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuit(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        return result;
    }

}
