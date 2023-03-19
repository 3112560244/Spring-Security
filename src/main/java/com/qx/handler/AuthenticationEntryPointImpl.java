package com.qx.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.qx.domain.ResponseResult;
import com.qx.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @Description 认证过程出现异常 鉴权失败
 * @Author ZedQ
 * @Date 2023/3/19 16:12
 * @Version 1.0
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.HTTP_UNAUTHORIZED, "用户认证失败，请重新登录");
        String json  = JSON.toJSONString(result);

        //处理异常
        WebUtils.renderString(response, json);
    }
}
