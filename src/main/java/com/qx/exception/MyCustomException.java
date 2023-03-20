package com.qx.exception;

import lombok.Data;

/**
 * TODO
 *
 * @Description 自定义异常
 * @Author ZedQ
 * @Date 2023/3/15 15:31
 * @Version 1.0
 **/
@Data
public class MyCustomException extends RuntimeException{

    private Integer code;

    /**
     * 401 Unauthorized：表示未授权，即没有提供有效的凭据（例如 token）。
     * 403 Forbidden：表示禁止访问，即已提供凭据但是不允许访问该资源。
     * 400 Bad Request：表示请求有误，包括 token 格式错误、过期等。
     * 422 Unprocessable Entity：表示无法处理请求，其中可能包括 token 错误。
     * @param code
     * @param message
     */
    public MyCustomException(Integer code,String message) {
        super(message);
        this.code = code;
    }


}
