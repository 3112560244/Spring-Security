package com.qx.service;

import com.qx.domain.ResponseResult;
import com.qx.domain.User;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/3/14 16:53
 * @Version 1.0
 **/
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}
