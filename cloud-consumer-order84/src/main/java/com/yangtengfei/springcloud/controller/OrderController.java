package com.yangtengfei.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yangtengfei.springcloud.common.bean.CommonResult;
import com.yangtengfei.springcloud.entities.Payment;
import com.yangtengfei.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
//@DefaultProperties(defaultFallback = "payment_time_out_handler")
public class OrderController {

    @Resource
    private PaymentService paymentService;


    //@HystrixCommand
    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(Long id) {
        return paymentService.getPaymentById(id);
    }

    public CommonResult payment_time_out_handler(Long id) {

        return new CommonResult(0, "12312312");
    }

    public CommonResult payment_time_out_handler() {

        return new CommonResult(0, "12312312");
    }
}
