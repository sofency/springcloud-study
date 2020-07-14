package com.sofency.top.service.impl;

import com.sofency.top.entities.Payment;
import org.springframework.stereotype.Service;
import com.sofency.top.dao.PaymentDao;
import com.sofency.top.service.PaymentService;

import javax.annotation.Resource;

/**
 * @author sofency
 * @date 2020/7/12 19:28
 * @package IntelliJ IDEA
 * @description
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
