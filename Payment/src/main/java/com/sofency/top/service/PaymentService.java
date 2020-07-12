package com.sofency.top.service;

import com.sofency.top.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author sofency
 * @date 2020/7/12 19:27
 * @package IntelliJ IDEA
 * @description
 */

public interface PaymentService {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id") Long id);

}
