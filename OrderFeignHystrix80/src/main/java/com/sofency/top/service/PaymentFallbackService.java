package com.sofency.top.service;

import org.springframework.stereotype.Component;

/**
 * @author sofency
 * @date 2020/7/18 10:46
 * @package IntelliJ IDEA
 * @description
 */
@Component
public class PaymentFallbackService implements PaymentService{
    @Override
    public String paymentInfo(Integer id) {
        return "消费方出现超时的情况"+id;
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "消费方出现超时的情况"+id;
    }
}
