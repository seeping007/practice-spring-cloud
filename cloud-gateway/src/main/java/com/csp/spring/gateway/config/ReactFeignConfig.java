package com.csp.spring.gateway.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "react-feign")
@NacosConfigurationProperties(prefix = "react-feign", dataId = "${spring.application.name}.yaml", autoRefreshed = true)
public class ReactFeignConfig {

    private long connectTimeout;

    private long writeTimeout;

    private long readTimeout;
}
