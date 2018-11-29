package com.win.world.order.service;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class FeignClientsConfig {

    @Bean
    Request.Options requestOptions(ConfigurableEnvironment env){
        int ribbonConnectionTimeout = env.getProperty("feign.client.config.default.connect-timeout", int.class, 5000);
        int ribbonReadTimeout = env.getProperty("feign.client.config.default.read-timeout", int.class, 15000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }

}
