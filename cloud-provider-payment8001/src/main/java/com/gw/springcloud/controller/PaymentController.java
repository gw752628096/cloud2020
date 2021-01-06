package com.gw.springcloud.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.gw.springcloud.entities.CommonResult;
import com.gw.springcloud.entities.Payment;
import com.gw.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private DruidDataSource druidDataSource;

    @Value("${server.port}")
    private String serverPort;


    @GetMapping(value = "payment/getDruidDataSource")
    public CommonResult<ResultSet> getPaymentById() {
        log.info("数据源:{}", druidDataSource.getClass());

        try (Connection connection = druidDataSource.getConnection()) {
            log.info("连接:{}  连接地址:{}", connection, connection.getMetaData().getURL());

            System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
            System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CommonResult<>(0, serverPort);
    }

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
