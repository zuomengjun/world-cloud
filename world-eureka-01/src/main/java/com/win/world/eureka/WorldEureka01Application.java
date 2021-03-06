package com.win.world.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WorldEureka01Application {

    public static void main(String[] args) {
        SpringApplication.run(WorldEureka01Application.class, args);
    }
}
