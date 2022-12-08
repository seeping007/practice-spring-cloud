package com.csp.spring.web.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CustomExceptionHandler
 *
 * @author chensiping
 * @since 2022-12-08
 */
public interface CustomExceptionHandler {

    ErrorModel handle(Exception e);

    @Data
    @AllArgsConstructor
    static class ErrorModel {

        private int status;

        private String type;

        private String message;
    }
}
