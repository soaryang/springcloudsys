package com.yangtengfei.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApplicationConfig {

    @Bean
    //@LoadBalanced
    public RestTemplate initRestTemplate() {
        return new RestTemplate();
    }
}
