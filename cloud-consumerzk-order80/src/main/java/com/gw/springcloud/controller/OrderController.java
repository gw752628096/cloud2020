package com.gw.springcloud.controller;

import com.gw.springcloud.entities.CommonResult;
import com.gw.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {
    public static final String INVOKE_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "consumer/payment/zk")
    public String paymentInfo(Payment payment) {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/zk", String.class);
        log.info("调用服务器结果:{}", result);
        return result;
    }
}
