package com.qx.controller;

import com.qx.domain.ResponseResult;
import com.qx.domain.Result;
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
    public ResponseResult<String> hello(){
        return Result.SUCCESS("hello admin");
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('system:user:list')")
    public ResponseResult user(){
        return Result.SUCCESS("hello user");
    }

    @GetMapping("/public")
    @PreAuthorize("hasAnyAuthority('system:dept:list','system:user:list')")
    public ResponseResult test(){
        return Result.SUCCESS("hello public");
    }


    @GetMapping("/customization")
    @PreAuthorize("@userPower.hasAuthority('system:user:list')")
    public ResponseResult customization(){
        return Result.SUCCESS("hello customization");
    }

    @GetMapping("/admin1")
    @PreAuthorize("@userPower.hasAuthority('system:dept:list')")
    public ResponseResult customization2(){
        return Result.SUCCESS("hello admin");
    }

    @GetMapping("/user1")
    @PreAuthorize("@userPower.hasAuthority('system:user:list')")
    public ResponseResult customization3(){
        return Result.SUCCESS("hello user");
    }


}
