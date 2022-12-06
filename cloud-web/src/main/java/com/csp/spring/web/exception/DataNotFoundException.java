package com.csp.spring.web.exception;

/**
 * Data not found exception
 * <p>
 * http status: 404
 *
 * @author chensiping
 * @since 2019-08-22
 **/
public class DataNotFoundException extends BusinessException {

    public DataNotFoundException(String message) {
        this("DATA_NOT_FOUND", message);
    }

    public DataNotFoundException(String type, String message) {
        super(404, type, message);
    }
}
