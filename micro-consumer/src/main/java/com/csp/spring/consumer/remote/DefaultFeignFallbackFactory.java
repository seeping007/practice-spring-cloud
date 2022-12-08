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

    @SneakyThrows
    @Override
    public T create(Throwable cause) {

        // 不需要进入降级的异常（上抛处理）
        if (cause instanceof FeignBusinessException || cause instanceof FeignException) {
            throw cause;
        }

        log.error("Fallback cause: {}", cause.getMessage());
        throw new RemoteException("REMOTE_FALLBACK", "Service not available");
    }
}
