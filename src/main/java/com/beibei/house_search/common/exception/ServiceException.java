package com.beibei.house_search.common.exception;

import com.beibei.house_search.common.errorcode.BaseErrorCode;
import com.beibei.house_search.common.errorcode.IErrorCode;

/**
 * 服务端异常
 */
public class ServiceException extends AbstractException{
    public ServiceException(IErrorCode iErrorCode) {
        this(null,null,iErrorCode);
    }

    public ServiceException(String message) {
        this(message,null, BaseErrorCode.SERVICE_ERROR);
    }

    public ServiceException(String message,IErrorCode iErrorCode) {
        this(message,null,iErrorCode);
    }

    public ServiceException(String message, Throwable throwable, IErrorCode iErrorCode) {
        super(message, throwable, iErrorCode);
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
