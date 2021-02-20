package com.yangtengfei.springcloud.service;

import com.yangtengfei.springcloud.common.bean.CommonResult;
import com.yangtengfei.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentService {

    @GetMapping("/getPaymentById")
    public CommonResult<Payment> getPaymentById(@RequestParam("id") Long id);
}
