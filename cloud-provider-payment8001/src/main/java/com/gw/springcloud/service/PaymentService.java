package com.gw.springcloud.service;

import com.gw.springcloud.entities.Payment;

public interface PaymentService {
    int save(Payment payment);

    Payment getPaymentById(Long id);
}
