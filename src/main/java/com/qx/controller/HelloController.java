package com.qx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/14 16:27
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello123";
    }

}
