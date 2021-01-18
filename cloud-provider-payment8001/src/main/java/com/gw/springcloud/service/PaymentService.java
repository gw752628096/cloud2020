package com.gw.springcloud.service;

import com.gw.springcloud.annotations.DataSource;
import com.gw.springcloud.entities.Payment;
import com.gw.springcloud.enums.DataSourceNames;

public interface PaymentService {

    @DataSource(name = DataSourceNames.MASTER)
    int save(Payment payment);

    Payment getPaymentById(Long id);
}
