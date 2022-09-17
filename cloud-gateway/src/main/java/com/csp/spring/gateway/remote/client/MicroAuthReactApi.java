package com.csp.spring.gateway.remote.client;

import com.csp.spring.gateway.model.CustomResponse;
import com.csp.spring.gateway.remote.model.OpenApiAuthReq;
import feign.RequestLine;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

/**
 * @author chensiping
 * @since 2022-09-17
 */
public interface MicroAuthReactApi {

    @RequestLine("POST /v1/openapi/auth")
    Mono<CustomResponse> authOpenApi(@RequestBody OpenApiAuthReq req);
}
