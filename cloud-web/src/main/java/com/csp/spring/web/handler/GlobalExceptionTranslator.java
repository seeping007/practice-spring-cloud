package com.csp.spring.web.handler;

import com.csp.spring.web.exception.BusinessException;
import com.csp.spring.web.model.CustomResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * Translate exception into error response
 * <p>
 * It's injected into {@link com.csp.spring.web.handler.GlobalExceptionHandler} as a spring bean.
 * So it must be extended by a subclass which declared as a spring bean.
 *
 * @author chensiping
 * @since 2021-01-14
 */
@Slf4j
public abstract class GlobalExceptionTranslator {

    protected abstract int serviceCode();

    public Object translate(String type, String message) {
        return CustomResponse.error(type, message);
    }

    public Object translate(BusinessException e) {
        return translate(e.getType(), e.getMessage());
    }

    public Object translateSuccess(Object body) {

        if (body instanceof CustomResponse) {
            return body;
        } else {
            return CustomResponse.ok(body);
        }
    }
}
