package com.yangtengfei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderApplication82 {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication82.class, args);
    }
}
