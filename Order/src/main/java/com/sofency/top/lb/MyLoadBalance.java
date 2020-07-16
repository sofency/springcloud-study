package com.sofency.top.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sofency
 * @date 2020/7/16 20:37
 * @package IntelliJ IDEA
 * @description
 */
@Component
public class MyLoadBalance implements LoadBalance{
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current= this.atomicInteger.get();
            next = current>=2147483647?0:(current+1);
        }while (!this.atomicInteger.compareAndSet(current,next));
        System.out.println("第几次访问*********next:"+next);
        return next;
    }



    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstanceList) {
        int current = getAndIncrement()% serviceInstanceList.size();
        return serviceInstanceList.get(current);//选择服务的结点
    }
}
