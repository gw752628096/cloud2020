package com.gw.springcloud.controller;

import com.gw.springcloud.entities.CommonResult;
import com.gw.springcloud.entities.Payment;
import com.gw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.util.List;

@RestController
@Slf4j
@RefreshScope
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping(value = "payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("services:{}", service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("server info:{} {} {} {}", instance.getServiceId(), instance.getHost(), instance.getPort(), instance.getUri());
        }

        return discoveryClient;
    }
}
