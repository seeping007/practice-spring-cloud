package com.csp.spring.gateway.remote;

import com.csp.spring.gateway.config.ReactFeignConfig;
import com.csp.spring.gateway.remote.client.MicroAuthReactApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.ReactiveOptions;
import reactivefeign.retry.BasicReactiveRetryPolicy;
import reactivefeign.spring.config.ReactiveRetryPolicies;
import reactivefeign.webclient.WebReactiveFeign;
import reactivefeign.webclient.WebReactiveOptions;

import javax.annotation.PostConstruct;

/**
 * @author chensiping
 * @since 2022-03-22
 */
@Slf4j
@Configuration
public class ReactiveFeignConfiguration {

    /**
     * ReactiveFeign目前只支持通过url调用服务
     */
    @Value("${remote-service.micro-auth.baseUrl}")
    private String microAuthBaseUrl;

    private final ReactFeignConfig reactFeignConfig;

    @PostConstruct
    public void init() {
        String url = microAuthBaseUrl;
    }

    public ReactiveFeignConfiguration(ReactFeignConfig reactFeignConfig) {
        this.reactFeignConfig = reactFeignConfig;
    }

    @Bean
    public ReactiveOptions reactiveOptions() {
        return new WebReactiveOptions.Builder()
                .setWriteTimeoutMillis(reactFeignConfig.getWriteTimeout())
                .setReadTimeoutMillis(reactFeignConfig.getReadTimeout())
                .setConnectTimeoutMillis(reactFeignConfig.getConnectTimeout())
                .build();
    }

    @Bean
    public ReactiveRetryPolicies retryOnNext() {
        // 不进行重试。retryOnSame是控制对同一个实例的重试策略，retryOnNext是控制对不同实例的重试策略
        return new ReactiveRetryPolicies.Builder()
                .retryOnSame(BasicReactiveRetryPolicy.retryWithBackoff(0, 10))
                .retryOnNext(BasicReactiveRetryPolicy.retryWithBackoff(0, 10)).build();
    }

    // ------------------- following: reactive feign clients -----------------------------------------------------------

    @Bean
    public MicroAuthReactApi microAuthReactApi() {
        log.info("[reactive-feign] microAuthBaseUrl: {}", microAuthBaseUrl);
        return WebReactiveFeign.<MicroAuthReactApi>builder()
                .target(MicroAuthReactApi.class, microAuthBaseUrl);
    }
}
