package com.csp.spring.web.exception;

/**
 * Business exception
 *
 * @author chensiping
 * @since 2019-08-21
 **/
public class BusinessException extends RuntimeException {

    // http status code
    private final int httpCode;

    // error type
    private final String type;
    
    public BusinessException(int httpCode, String type, String message) {
        super(message);
        this.httpCode = httpCode;
        this.type = type;
    }
    public BusinessException(int httpCode, String type, String message, Throwable cause) {
        super(message, cause);
        this.httpCode = httpCode;
        this.type = type;
    }

    public BusinessException(String type, String message) {
        super(message);
        this.httpCode = 500;
        this.type = type;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getType() {
        return type;
    }
}
