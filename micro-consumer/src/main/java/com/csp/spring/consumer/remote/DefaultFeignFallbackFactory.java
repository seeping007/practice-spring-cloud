package com.csp.spring.consumer.remote;

import com.csp.spring.web.exception.FeignBusinessException;
import com.csp.spring.web.exception.RemoteException;
import feign.FeignException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author chensiping
 * @since 2022-12-08
 */
@Slf4j
public class DefaultFeignFallbackFactory<T> implements FallbackFactory<T> {

    // 若开启了circuitbreaker（feign.circuitbreaker.enabled=true），出现异常或触发熔断都会进入fallback
    @SneakyThrows
    @Override
    public T create(Throwable cause) {

        // 不需要进入降级的异常（上抛处理）
        if (cause instanceof FeignBusinessException || cause instanceof FeignException) {
            throw cause;
        }

        // 默认降级处理（抛503）
        log.error("Fallback cause: {}, {}", cause.getClass().getSimpleName(), cause.getMessage());
        throw new RemoteException(503, "REMOTE_FALLBACK", "Service not available");
    }
}
