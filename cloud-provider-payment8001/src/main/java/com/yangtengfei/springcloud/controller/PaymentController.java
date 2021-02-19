package com.yangtengfei.springcloud.controller;

import com.alibaba.fastjson.JSON;
import com.yangtengfei.springcloud.common.bean.CommonResult;
import com.yangtengfei.springcloud.entities.Payment;
import com.yangtengfei.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/findDiscovery")
    public CommonResult discoveryClient() {

        List<String> services = discoveryClient.getServices();

        log.info("services:{}", JSON.toJSONString(services));
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for(ServiceInstance serviceInstance:instances){
            log.info(serviceInstance.getInstanceId()+"\t"+serviceInstance.getHost()+"\t"+serviceInstance.getPort()+"\t"+serviceInstance.getUri());
        }
        //log.info("instances:{}", JSON.toJSONString(instances));

        return new CommonResult(200, "success", null);
    }

    @PostMapping("/insert")
    public CommonResult commonResult(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("insert result:" + result);


        CommonResult<Integer> commonResult = new CommonResult<Integer>();
        if (result > 0) {
            return new CommonResult(200, "success", result);
        }
        return new CommonResult(500, "failed", null);
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("123123123123");

        log.info("vvvvvvvvvvvvvvvvvvvvvvvvvv");
        if (payment != null) {
            return new CommonResult(200, "success", payment);
        }
        return new CommonResult(500, "failed", null);
    }
}
