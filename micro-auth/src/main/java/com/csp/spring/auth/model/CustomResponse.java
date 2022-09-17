package com.csp.spring.auth.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@Data
@Builder
public class CustomResponse {

    private String status;

    private String message;

    private Object data;
}
