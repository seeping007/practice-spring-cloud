package com.csp.spring.web.exception;

/**
 * Unauthorized exception
 * <p>
 * http status: 401
 *
 * @author chensiping
 * @since 2021-01-14
 */
public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(String message) {
        this("UNAUTHORIZED", message);
    }

    public UnauthorizedException(String type, String message) {
        super(401, type, message);
    }
}
