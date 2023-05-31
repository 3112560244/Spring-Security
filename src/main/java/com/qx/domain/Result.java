package com.qx.domain;

import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 * TODO
 *
 * @Description
 * @Author ZedQ
 * @Date 2023/5/28 16:09
 * @Version 1.0
 **/
public class Result {

    /**
     * 成功
     */
    public static <T> ResponseResult<T> SUCCESS(T t){
        return new ResponseResult<T>(200,"成功",t);
    }

    /**
     * 错误
     */
    public static <T> ResponseResult<T> ERROR(String msg){
        return new ResponseResult<T>(200,msg);
    }

//    static ResponseResult ERROR(String msg){
//        return new ResponseResult(200,msg);
//    }


}
