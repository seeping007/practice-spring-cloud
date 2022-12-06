package com.csp.spring.web.exception;

/**
 * Forbidden exception
 * <p>
 * http status: 403
 *
 * @author chensiping
 * @since 2020-11-13
 */
public class ForbiddenException extends BusinessException {

    public ForbiddenException(String message) {
        this("FORBIDDEN", message);
    }

    public ForbiddenException(String type, String message) {
        super(403, type, message);
    }
}
