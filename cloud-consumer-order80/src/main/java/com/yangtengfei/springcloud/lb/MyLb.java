package com.yangtengfei.springcloud.lb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class MyLb implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current, next));
        log.info("************************next" + next);
        return next;
    }

    public ServiceInstance instance(List<ServiceInstance> serviceInstanceList) {

        int index = getAndIncrement() % serviceInstanceList.size();
        ServiceInstance serviceInstance = serviceInstanceList.get(index);
        return serviceInstance;
    }
}
