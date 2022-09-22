package com.csp.spring.producer.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chensiping
 * @since 2022-09-21
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ext-config")
@NacosConfigurationProperties(prefix = "ext-config", dataId = "micro-producer-ext-config.yaml", autoRefreshed = true)
public class ExtConfig {

    private String desc;
}
