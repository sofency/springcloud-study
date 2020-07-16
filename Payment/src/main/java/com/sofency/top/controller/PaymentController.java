package com.sofency.top.controller;

import com.sofency.top.entities.CommonResult;
import com.sofency.top.entities.Payment;
import com.sofency.top.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author sofency
 * @date 2020/7/12 19:33
 * @package IntelliJ IDEA
 * @description
 */
@RestController
@Slf4j
public class PaymentController {

    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;//服务发现

    @Autowired
    public PaymentController(PaymentService paymentService){
        this.paymentService=paymentService;
    }
    //作为服务提供者时传的对象参数必须传RequestBody 不然插入的数据为null
    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment){

        System.out.println(payment.toString());
        int result = paymentService.create(payment);
        log.info("*******插入结果,"+result+"*******");
        if(result>0){
            return new CommonResult(200,"插入数据库成功serverPort:"+serverPort,result);
        }else{
            return new CommonResult(404,"插入数据库失败");
        }

    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("*******获取成功,"+payment+"*******");
        if(payment!=null){
            System.out.printf(payment.toString());
            return new CommonResult(200,"获取数据成功serverPort:"+serverPort,payment);
        }else{
            return new CommonResult(404,"查询失败");
        }
    }


    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach(service->{
            log.info("element****"+service);
        });
        List<ServiceInstance> payment = discoveryClient.getInstances("PAYMENT");
        payment.forEach(service->{
            log.info("service****"+service.getInstanceId()+"\t"+service.getHost()+"\t"+service.getPort());
        });
        return services;
    }


    //使用自己的轮询算法实现负载均衡
    @GetMapping("/payment/lb")
    public String getPaymentPort(){
        return serverPort;
    }

    //超时
    @GetMapping("/payment/get/timeout")
    public CommonResult timeout() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return new CommonResult(200,"插入数据库成功serverPort:"+serverPort);
    }
}
