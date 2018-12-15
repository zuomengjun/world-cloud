package com.win.world.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 降级controller
 */
@RestController
public class FallBackController {

    /**
     * 降级处理
     * 注：此处无法打印异常信息
     */
    @GetMapping("fallBackUri")
    public String fallBack(){
        return "web服务降级了";
    }

}
