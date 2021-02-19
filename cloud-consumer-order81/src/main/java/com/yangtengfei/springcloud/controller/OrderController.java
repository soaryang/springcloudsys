package com.yangtengfei.springcloud.controller;

import com.yangtengfei.springcloud.common.bean.CommonResult;
import com.yangtengfei.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/insert")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/insert", payment, CommonResult.class);
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/getPaymentById?id=" + id, CommonResult.class);
    }
}
