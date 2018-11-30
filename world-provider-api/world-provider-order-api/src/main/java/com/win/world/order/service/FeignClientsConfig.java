package com.win.world.order.service;

import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

@Configuration
public class FeignClientsConfig {

    /**
     * ribbon重试机制：
     *      由于feign和ribbion都具备重试机制，一起使用会造成混乱，默认feign已经禁用重试机制，
     *      ribbon默认重试一次，ribbon配置重试：ribbon.MaxAutoRetries、ribbon.MaxAutoRetriesNextServer，
     *      ribbon重试次数 = MaxAutoRetries + MaxAutoRetriesNextServer + (MaxAutoRetries * MaxAutoRetriesNextServer)，
     *      ribbon默认情况下，get请求无论是连接异常还是读取异常都会重试，非get请求只在连接异常时重试
     * ribbon连接超时和读取超时
     *      feign和ribbon都可配置，都不配置默认取ribbon的超时时间，配置类：RibbonClientConfiguration，ribbon配置超时时间：ribbon.ConnectTimeout、ribbon.ReadTimeout，
     *      都配置了则取feign的超时时间，feign全局配置：feign.client.config.default.connect-timeout、feign.client.config.default.read-timeout，
     *      feign单个服务配置：feign.client.config.服务名.connect-timeout、feign.client.config.服务名.read-timeout
     */
    @Bean
    Request.Options requestOptions(ConfigurableEnvironment env){
        int ribbonConnectionTimeout = env.getProperty("feign.client.config.default.connect-timeout", int.class, 5000);
        int ribbonReadTimeout = env.getProperty("feign.client.config.default.read-timeout", int.class, 15000);

        return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
    }

}
