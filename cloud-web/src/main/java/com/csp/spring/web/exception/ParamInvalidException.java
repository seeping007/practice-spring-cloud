package com.csp.spring.web.exception;

/**
 * Param invalid exception
 * <p>
 * http status: 400
 *
 * @author chensiping
 * @since 2019-08-21
 **/
public class ParamInvalidException extends BusinessException {

    public ParamInvalidException(String message) {
        this("PARAM_INVALID", message);
    }

    public ParamInvalidException(String type, String message) {
        super(400, type, message);
    }
}
