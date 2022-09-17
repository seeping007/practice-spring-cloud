package com.csp.spring.gateway.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author chensiping
 * @since 2022-01-26
 */
public class CustomResponseException extends ResponseStatusException {

    private String type;

    public CustomResponseException(HttpStatus status) {
        super(status);
        this.type = status.name();
    }

    public CustomResponseException(HttpStatus status, String reason) {
        super(status, reason);
        this.type = status.name();
    }

    public CustomResponseException(HttpStatus status, String type, String reason) {
        super(status, reason);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
