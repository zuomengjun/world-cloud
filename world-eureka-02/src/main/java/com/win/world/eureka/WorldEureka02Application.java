package com.win.world.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WorldEureka02Application {

    public static void main(String[] args) {
        SpringApplication.run(WorldEureka02Application.class, args);
    }
}
