package com.qx.controller;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public String hello(){
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('user')")
    public String user(){
        return "user";
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('test')")
    public String test(){
        return "test";
    }

}
