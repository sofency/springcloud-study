package com.sofency.top.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sofency.top.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sofency
 * @date 2020/7/17 22:44
 * @package IntelliJ IDEA
 * @description
 */
@RestController
//@DefaultProperties(defaultFallback = "orderInfoTimeoutHandler")  //设置默认的服务降级调用的方法
public class OrderController {

    @Resource
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String getHystrix(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo(id);
        return result;
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "orderInfoTimeoutHandler",commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="4000")
//
//    })
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    public String paymentInfoTimeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfoTimeout(id);
        return result;
    }

//    //注意参数保持一致
//    public String orderInfoTimeoutHandler(){
//        return "消费方出现错误";
//    }
}
