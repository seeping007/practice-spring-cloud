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
@ConfigurationProperties(prefix = "main-config")
@NacosConfigurationProperties(prefix = "main-config", dataId = "micro-producer.yaml", autoRefreshed = true)
public class MainConfig {

    private String desc;

    private String helloPass;
}
