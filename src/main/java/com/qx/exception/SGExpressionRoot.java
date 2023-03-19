package com.qx.exception;

import com.qx.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/19 19:25
 * @Version 1.0
 **/
@Component("userPower")
public class SGExpressionRoot {

    public boolean hasAuthority(String authority){
        //获取当前用户的权限
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        //判断用户权限集合中是否存在authority

        //后续加入session判断用户权限    session中存储用户权限 通过session判断用户是否有权限
        //TODO 加入session判断用户权限


        //如果存在返回true
        return permissions.contains(authority);
    }
}
