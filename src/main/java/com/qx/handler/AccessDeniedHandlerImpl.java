package com.qx.handler;

import cn.hutool.http.HttpStatus;
import com.alibaba.fastjson.JSON;
import com.qx.domain.ResponseResult;
import com.qx.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @Description 授权过程出现异常  权限不足
 * @Author ZedQ
 * @Date 2023/3/19 16:55
 * @Version 1.0
 **/
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(HttpStatus.HTTP_FORBIDDEN, "权限不足，请联系管理员");
        String json  = JSON.toJSONString(result);

        //处理异常
        WebUtils.renderString(response, json);
    }
}
