package com.win.world.order.controller;

import com.win.world.order.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@RefreshScope
public class OrderController {

    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @Value("${spring.mvc.date-format}")
    private String mvcDateFormat;

    @Value("${mypassword}")
    private String mypassword;

    @GetMapping("getPort")
    public String getPort(){
        System.out.println("I am a Port : "+port);
        return port;
    }

    @GetMapping("getMvcDateFormat")
    public String getMvcDateFormat(){
        return mvcDateFormat;
    }

    @GetMapping("getMypassword")
    public String getMypassword(){
        return mypassword;
    }

    @GetMapping("get/{id}")
    public Order get(@PathVariable String id){
        Order order = new Order();
        order.setId(id);
        order.setName("电视");
        return order;
    }

    @GetMapping("getServiceInstances")
    public Object getServiceInstances(){
        List<String> services = discoveryClient.getServices();
        System.out.println(services);

        List<ServiceInstance> instances = discoveryClient.getInstances("world-provider-order");
        for (ServiceInstance instance : instances) {
            System.out.println(instance.getServiceId() + ":" + instance.getHost() + ":" + instance.getPort() + ":" + instance.getUri());
        }

        return discoveryClient;
    }
}
