package com.yangtengfei.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderApplication8003 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication8003.class, args);
    }
}
