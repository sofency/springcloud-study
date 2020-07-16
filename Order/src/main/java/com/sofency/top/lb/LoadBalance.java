package com.sofency.top.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author sofency
 * @date 2020/7/16 20:32
 * @package IntelliJ IDEA
 * @description
 */
public interface LoadBalance {

    //获取
    ServiceInstance instances(List<ServiceInstance> serviceInstanceList);
}
