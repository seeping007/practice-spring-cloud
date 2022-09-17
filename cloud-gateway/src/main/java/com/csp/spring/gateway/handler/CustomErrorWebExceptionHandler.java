package com.csp.spring.gateway.handler;

import com.csp.spring.gateway.model.CustomResponse;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom ErrorWebExceptionHandler
 *
 * @author chensiping
 * @since 2020-10-10
 */
public class CustomErrorWebExceptionHandler extends DefaultErrorWebExceptionHandler {

    public CustomErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties.Resources resources,
                                          ErrorProperties errorProperties, ApplicationContext applicationContext) {
        super(errorAttributes, resources, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {

        int code;
        CustomResponse customResponse;

        // custom processing by exception type
        Throwable error = super.getError(request);
        if (error instanceof CustomResponseException errorException) {
            code = errorException.getStatus().value();
            customResponse = CustomResponse.builder()
                    .status(errorException.getType()).message(errorException.getMessage()).build();
        } else if (error instanceof ResponseStatusException errorException) {
            code = errorException.getStatus().value();
            customResponse = CustomResponse.builder()
                    .status(errorException.getStatus().name()).message(errorException.getReason()).build();
        } else {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            customResponse = CustomResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.name()).message(error.getMessage()).build();
        }

        Map<String, Object> errorAttributes = new HashMap<>(4);
        errorAttributes.put("code", code); //
        errorAttributes.put("status", customResponse.getStatus());
        errorAttributes.put("message", customResponse.getMessage());
        return errorAttributes;
    }

    @Override
    protected int getHttpStatus(Map<String, Object> errorAttributes) {
        // custom http status by code attribute
        int code = (Integer) errorAttributes.get("code");
        errorAttributes.remove("code"); //
        return code;
    }
}
