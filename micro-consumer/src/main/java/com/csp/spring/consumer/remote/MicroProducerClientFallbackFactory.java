package com.csp.spring.consumer.remote;

import com.csp.spring.consumer.remote.model.HelloVO;
import com.csp.spring.web.exception.FeignBusinessException;
import com.csp.spring.web.exception.RemoteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author chensiping
 * @since 2022-12-06
 */
@Slf4j
@Component
public class MicroProducerClientFallbackFactory implements FallbackFactory<MicroProducerClient> {

    // 若开启了circuitbreaker（feign.circuitbreaker.enabled=true），出现异常或断路器开启都会进入fallback
    @Override
    public MicroProducerClient create(Throwable cause) {

        // 不纳入计算的异常不需要进入降级
        if (cause instanceof FeignBusinessException) {
            throw (FeignBusinessException) cause;
        }

        return new MicroProducerClient() {

            @Override
            public HelloVO hello(String name) {
                log.error("Fallback cause: {}", cause.getMessage());
                throw new RemoteException("Service not available");
            }
        };
    }
}
