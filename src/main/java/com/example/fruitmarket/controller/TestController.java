package com.example.fruitmarket.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "水果超市系统后端启动成功，数据库连接就绪！";
    }
}