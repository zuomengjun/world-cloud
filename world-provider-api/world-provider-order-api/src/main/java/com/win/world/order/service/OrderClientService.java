package com.win.world.order.service;

import com.win.world.order.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "world-provider-order", path = "order", fallbackFactory = OrderClientServiceFallBackFactory.class)
public interface OrderClientService {

    @GetMapping("getPort")
    String getPort();

    @GetMapping("getMvcDateFormat")
    String getMvcDateFormat();

    @GetMapping("getMypassword")
    String getMypassword();

    @GetMapping("get/{id}")
    Order get(@PathVariable("id") String id);

    @GetMapping("getServiceInstances")
    Object getServiceInstances();

}
