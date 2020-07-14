package com.sofency.top.dao;

import com.sofency.top.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author sofency
 * @date 2020/7/12 19:07
 * @package IntelliJ IDEA
 * @description
 */
@Mapper
public interface PaymentDao {
    public int create(Payment payment);
    public Payment getPaymentById(@Param("id")Long id);
}
