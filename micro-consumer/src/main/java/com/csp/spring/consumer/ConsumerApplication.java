package com.csp.spring.consumer;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author CSP
 * @since 2019-12-25
 */
@EnableDiscoveryClient
@EnableFeignClients
@NacosPropertySource(dataId = "micro-consumer.yaml", autoRefreshed = true, type = ConfigType.YAML)
@SpringBootApplication
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
