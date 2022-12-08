package com.csp.spring.consumer.remote;

import com.csp.spring.web.exception.FeignBusinessException;
import com.csp.spring.web.handler.CustomExceptionHandler;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chensiping
 * @since 2022-12-08
 */
@Slf4j
@Component
public class FeignBusinessExceptionHandler implements CustomExceptionHandler {

    @Override
    public CustomExceptionHandler.ErrorModel handle(Exception e) {

        if (e instanceof final FeignException ex) { // feign默认异常（Connection refused, Read timeout, ...）

            final ErrorModel errorModel = new ErrorModel(500, "REMOTE_ERROR",
                    ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage());

            log.warn("FeignBusinessExceptionHandler: status={}, message={}", ex.status(), ex.getMessage());
            return errorModel;

        } else if (e instanceof final FeignBusinessException ex) { // feign自定义业务异常

            final ErrorModel errorModel = new ErrorModel(ex.getHttpCode(), ex.getType(), e.getMessage());

            log.warn("FeignBusinessExceptionHandler: status={}, type={}, message={}",
                    errorModel.getStatus(), errorModel.getType(), errorModel.getMessage());
            return errorModel;

        } else {
            return null;
        }
    }
}
