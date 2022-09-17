package com.csp.spring.auth.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@Configuration
@ConfigurationProperties(prefix = "app-info")
public class AppInfoConfig {

    @Setter
    @Getter
    private List<AppConfig> apps;

    @Getter
    private Map<String, AppConfig> appMap;

    @PostConstruct
    public void init() {
        appMap = new HashMap<>();
        apps.forEach(appConfig -> appMap.put(appConfig.getAppKey(), appConfig));
    }

    public AppConfig getAppConfig(String appKey) {
        return appMap.get(appKey);
    }
}
