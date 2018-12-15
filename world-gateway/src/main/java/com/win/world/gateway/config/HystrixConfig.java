package com.win.world.gateway.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class HystrixConfig {

    @PostConstruct
    public void HystrixProperties(){
        String hystrixTimeoutEnable = System.getProperty("hystrix.command.default.execution.timeout.enabled");
        String hystrixTimeout = System.getProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds");
        // 信号量隔离策略下最大并发线程数
        String maxConcurrentRequests = System.getProperty("hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests");
        if (StringUtils.isBlank(hystrixTimeoutEnable)){
            System.setProperty("hystrix.command.default.execution.timeout.enabled", "true");
        }
        if (StringUtils.isBlank(hystrixTimeout)){
            System.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", "30000");
        }
        if (StringUtils.isBlank(maxConcurrentRequests)){
            System.setProperty("hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests", "1000");
        }
    }
}
