package com.yangtengfei.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yangtengfei.springcloud.dao.PaymentDao;
import com.yangtengfei.springcloud.entities.Payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment) {
        return paymentDao.create(payment);
    }


    @HystrixCommand(fallbackMethod = "payment_time_out_handler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    public Payment payment_time_out_handler(Long id){

        return new Payment(0,"12312312");
    }
}
