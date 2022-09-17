package com.csp.spring.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse {

    private String status;

    private String message;

    private Object data;
}
