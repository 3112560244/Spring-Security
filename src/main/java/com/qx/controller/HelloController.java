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
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String hello(){
        return "admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('system:user:list')")
    public String user(){
        return "user";
    }

    @GetMapping("/public")
    @PreAuthorize("hasAnyAuthority('system:dept:list','system:user:list')")
    public String test(){
        return "public";
    }


    @GetMapping("/customization")
    @PreAuthorize("@userPower.hasAuthority('system:user:list')")
    public String customization(){
        return "public";
    }
}
