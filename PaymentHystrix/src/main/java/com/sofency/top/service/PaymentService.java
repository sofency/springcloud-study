package com.sofency.top.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @author sofency
 * @date 2020/7/17 21:37
 * @package IntelliJ IDEA
 * @description
 */
@Service
public class PaymentService {
    //正常访问
    public String paymentInfo(Integer id){
        return "线程池"+Thread.currentThread().getName()+"paymentInfo,id="+id+"\t";
    }

    //正常访问
    //设置兜底的解决方案
    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="5000")

    })
    public String paymentInfoTimeout(Integer id){
        int  timeNumber=3;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"paymentInfo,id="+id+"\t耗时"+timeNumber+"s";
    }


    public String paymentInfoTimeoutHandler(Integer id){

        return "线程池"+Thread.currentThread().getName()+"\t paymentInfoTimeoutHandler,id="+id+"\tfallback";
    }

    //服务熔断
    @HystrixCommand(
            fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value="true"),//是否开启熔断器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value="10"), //请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value="10000"),//时间窗口器
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value="60")//失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
            throw new RuntimeException("**** id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功 流水号是"+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能为复数 请稍后从事 id="+id;
    }

}
