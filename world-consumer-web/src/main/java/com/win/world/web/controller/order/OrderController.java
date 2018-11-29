package com.win.world.web.controller.order;

import com.win.world.order.entity.Order;
import com.win.world.order.service.OrderClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("order")
public class OrderController {

    @Resource
    private OrderClientService orderClientService;

    @GetMapping("getPort")
    public String getPort(){
        return orderClientService.getPort();
    }

    @GetMapping("getMvcDateFormat")
    public String getMvcDateFormat(){
        return orderClientService.getMvcDateFormat();
    }

    @GetMapping("getMypassword")
    public String getMypassword(){
        return orderClientService.getMypassword();
    }

    @GetMapping("get/{id}")
    public Order get(@PathVariable String id){
        return orderClientService.get(id);
    }

    @GetMapping("getServiceInstances")
    public Object getServiceInstances(){
        return orderClientService.getServiceInstances();
    }

}
