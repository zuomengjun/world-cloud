package com.win.world.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.win.world"})
@EnableFeignClients(basePackages = {"com.win.world"})
@EnableHystrix
public class WorldConsumerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorldConsumerWebApplication.class, args);
    }
}
