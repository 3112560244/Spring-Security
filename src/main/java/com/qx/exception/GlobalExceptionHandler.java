package com.qx.exception;

import com.qx.domain.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ZedQ
 * @Description: 业务异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    //捕捉到 之后直接返回result
    @ExceptionHandler(MyCustomException.class)
    @ResponseBody
    public ResponseResult handle(MyCustomException se){
        return new ResponseResult(se.getCode(),se.getMessage());
    }
}
