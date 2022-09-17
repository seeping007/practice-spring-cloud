package com.csp.spring.gateway.remote.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@Data
@Builder
public class OpenApiAuthReq {

    private String appKey;
}
