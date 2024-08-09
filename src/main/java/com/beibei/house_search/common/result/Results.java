package com.beibei.house_search.common.result;

import com.beibei.house_search.common.errorcode.BaseErrorCode;
import com.beibei.house_search.common.exception.AbstractException;

import java.util.Optional;

/**
 * 构造全局响应对象
 */
public final class Results {

    /**
     * 成功无参响应
     */
    public static Result<Void> success(){
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }


    /**
     * 带参数成功响应
     */
    public static <T> Result<T> success(T data){
        return new Result<T>()
                .setData(data)
                .setCode(Result.SUCCESS_CODE);
    }

    /**
     * 服务端失效响应
     */
    public static Result<Void> failure(){
        return new Result<Void>()
                .setCode(BaseErrorCode.SERVICE_ERROR.code())
                .setMessage(BaseErrorCode.SERVICE_ERROR.message());
    }

    /**
     * 通过 {@link AbstractException} 构造失效响应
     */
    public static Result<Void> failure(AbstractException abstractException){
        String errorCode = Optional.ofNullable(abstractException.getErrorCode())
                .orElse(BaseErrorCode.SERVICE_ERROR.code());
        String errorMessage = Optional.ofNullable(abstractException.getErrorMessage())
                .orElse(BaseErrorCode.SERVICE_ERROR.message());
        return new Result<Void>()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }

    /**
     * 通过错误码和信息构造失效响应
     */
    public static Result<Void> failure(String errorCode, String errorMessage){
        return new Result<Void>()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }
}
