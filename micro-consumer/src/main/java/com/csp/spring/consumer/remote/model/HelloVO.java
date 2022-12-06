package com.csp.spring.consumer.remote.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author chensiping
 * @since 2022-09-21
 */
@Builder
@Data
public class HelloVO {

    private String name;

    private Long timestamp;

    private Object mainConfig;

    private Object extConfig;

    private String mainConfigDesc;
}
