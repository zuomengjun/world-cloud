package com.win.world.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class WorldConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorldConfigApplication.class, args);
    }
}
