package com.gw.springcloud.dao;

import com.gw.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentDao {
    public int save(Payment payment);

    public Payment getPaymentById(Long id);
}
