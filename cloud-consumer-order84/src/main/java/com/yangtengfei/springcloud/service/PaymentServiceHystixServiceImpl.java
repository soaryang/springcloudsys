package com.yangtengfei.springcloud.service;

import com.yangtengfei.springcloud.common.bean.CommonResult;
import com.yangtengfei.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceHystixServiceImpl implements PaymentService {

    public CommonResult<Payment> getPaymentById(Long id) {
        return null;
    }
}
