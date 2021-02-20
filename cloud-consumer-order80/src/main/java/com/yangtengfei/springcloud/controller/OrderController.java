package com.yangtengfei.springcloud.controller;

import com.sun.jndi.toolkit.url.Uri;
import com.yangtengfei.springcloud.common.bean.CommonResult;
import com.yangtengfei.springcloud.entities.Payment;
import com.yangtengfei.springcloud.lb.LoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {

    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @Resource
    private LoadBalancer loadBalancer;

    @PostMapping("/insert")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/insert", payment, CommonResult.class);
    }

    @GetMapping("/getPaymentById")
    public CommonResult getPaymentById(Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/getPaymentById?id=" + id, CommonResult.class);
    }

    @GetMapping("/getPaymentById2")
    public CommonResult ResponseEntity(Long id) {
        //return restTemplate.getForObject(PAYMENT_URL + "/getPaymentById?id=" + id, CommonResult.class);
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/getPaymentById?id=" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return new CommonResult(444, "操作失败");
    }

    @GetMapping("/getPaymentById3")
    public CommonResult discoveryClient(Long id) {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (!CollectionUtils.isEmpty(serviceInstances)) {
            ServiceInstance serviceInstance = loadBalancer.instance(serviceInstances);
            URI uri = serviceInstance.getUri();
            String targetUrl = uri + "/getPaymentById?id=" + id;
            return restTemplate.getForObject(targetUrl, CommonResult.class);
        }
        return new CommonResult(444, "操作失败");
    }
}
