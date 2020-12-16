package com.gw.springcloud.controller;

import com.gw.springcloud.entities.CommonResult;
import com.gw.springcloud.entities.Payment;
import com.gw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "payment/save")
    public CommonResult<Integer> save(@RequestBody Payment payment) {
        int save = paymentService.save(payment);
        log.info("插入结果:{} serverPort:{}", save, serverPort);
        return new CommonResult<>(save, serverPort);
    }

    @GetMapping(value = "payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("查询结果:{} serverPort:{}", payment, serverPort);
        return new CommonResult<>(0, serverPort, payment);
    }
}
