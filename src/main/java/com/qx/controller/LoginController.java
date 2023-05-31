package com.qx.controller;

import com.qx.domain.ResponseResult;
import com.qx.domain.User;
import com.qx.exception.MyCustomException;
import com.qx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/14 16:53
 * @Version 1.0
 *
 **/
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginService.login(user);
    }

    @RequestMapping("/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @GetMapping("/test")
    public ResponseResult test(){
        new MyCustomException(401,"测试失败");
        return new ResponseResult(200,"测试成功","");
    }

}
