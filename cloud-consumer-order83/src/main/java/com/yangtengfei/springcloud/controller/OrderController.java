package com.yangtengfei.springcloud.controller;

import com.yangtengfei.springcloud.common.bean.CommonResult;
import com.yangtengfei.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderController {

    @Resource
    private PaymentService paymentService;

//    public static final String PAYMENT_URL = "http://cloud-payment-service";
//
//
//    @PostMapping("/insert")
//    public CommonResult<Payment> create(@RequestBody Payment payment) {
//        return restTemplate.postForObject(PAYMENT_URL + "/insert", payment, CommonResult.class);
//    }
//
    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(Long id) {
        return  paymentService.getPaymentById(id);
    }
}
