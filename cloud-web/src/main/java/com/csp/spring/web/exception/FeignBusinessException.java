package com.csp.spring.web.exception;

/**
 * Feign被调用方的业务异常
 *
 * @author chensiping
 * @since 2021-03-09
 */
public class FeignBusinessException extends RuntimeException {

    private final int httpCode;
    private final String type;

    public FeignBusinessException(String type, String message) {
        this(500, type, message);
    }

    public FeignBusinessException(int httpCode, String type, String message) {
        super(message);
        this.httpCode = httpCode;
        this.type = type;
    }

    public int getHttpCode() {
        return this.httpCode;
    }

    public String getType() {
        return this.type;
    }
}
