package com.beibei.house_search.common.exception;

import com.beibei.house_search.common.errorcode.IErrorCode;
import lombok.Getter;

/**
 * 抽象异常处理器
 */
@Getter
public abstract class AbstractException extends RuntimeException{
    public final String errorCode;
    public final String errorMessage;

    public AbstractException(String message, Throwable throwable, IErrorCode iErrorCode) {
        super(message,throwable);
        this.errorCode = iErrorCode.code();
        this.errorMessage = iErrorCode.message();
    }
}
