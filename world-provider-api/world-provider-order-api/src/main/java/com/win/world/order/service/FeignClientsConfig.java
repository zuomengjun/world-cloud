package com.win.world.order.service;

import feign.Request;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.PostConstruct;

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

    /**
     * hystrix配置单个接口属性：
     *   1、@HystrixCommand注解配置在调用feign接口的controller方法上且@HystrixCommand注解内配置@HystrixProperty注解(属性如：execution.isolation.thread.timeoutInMilliseconds)
     *   2、配置文件内配置属性，例如：hystrix.command.OrderClientService#get(String).execution.isolation.thread.timeoutInMilliseconds(commandKey规则：接口名#方法名(参数类型))
     * hystrix各参数配置释义：
     *   https://blog.csdn.net/WYA1993/article/details/82352890
     * hystrix配置属性类
     *   HystrixCommandProperties、HystrixThreadPoolProperties
     * 在配置文件中配置hystrix默认属性的缺点：
     *   每个服务调用方都需要在配置文件中配置，此处在api包中设置系统属性，凡是引用该api包的调用方以下系统属性皆生效
     */
    @PostConstruct
    public void FeignProperties(){
        String hystrixTimeoutEnable = System.getProperty("hystrix.command.default.execution.timeout.enabled");
        String hystrixTimeout = System.getProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds");
        if (StringUtils.isBlank(hystrixTimeoutEnable)){
            System.setProperty("hystrix.command.default.execution.timeout.enabled", "true");
        }
        if (StringUtils.isBlank(hystrixTimeout)){
            System.setProperty("hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds", "30000");
        }
    }

}
