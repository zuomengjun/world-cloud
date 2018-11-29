package com.win.world.order.service;

import com.win.world.order.entity.Order;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderClientServiceFallBackFactory implements FallbackFactory<OrderClientService> {

    private static final Logger log = LoggerFactory.getLogger(OrderClientServiceFallBackFactory.class);

    @Override
    public OrderClientService create(Throwable throwable) {
        return new OrderClientService() {
            @Override
            public String getPort() {
                log.error("getPort接口熔断, 异常信息: {}", throwable.fillInStackTrace());
                return "no port";
            }

            @Override
            public String getMvcDateFormat() {
                log.error("getMvcDateFormat接口熔断, 异常信息: {}", throwable.fillInStackTrace());
                return "no mvcDateFormat";
            }

            @Override
            public String getMypassword() {
                log.error("getMypassword接口熔断, 异常信息: {}", throwable.fillInStackTrace());
                return "no mypassword";
            }

            @Override
            public Order get(String id) {
                log.error("get接口熔断, 异常信息: {}", throwable.fillInStackTrace());
                Order order = new Order();
                order.setId("0");
                order.setName("无此单");
                return order;
            }

            @Override
            public Object getServiceInstances() {
                log.error("getServiceInstances接口熔断, 异常信息: {}", throwable.fillInStackTrace());
                return "无";
            }
        };
    }
}
