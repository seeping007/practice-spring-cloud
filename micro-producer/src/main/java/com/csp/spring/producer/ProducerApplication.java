package com.csp.spring.producer;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CSP
 * @since 2019-12-25
 */
@NacosPropertySource(dataId = "micro-producer.yaml", autoRefreshed = true, type = ConfigType.YAML)
@SpringBootApplication
public class ProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
