package com.csp.spring.gateway.remote;

import com.csp.spring.gateway.model.CustomResponse;
import com.csp.spring.gateway.remote.client.MicroAuthReactApi;
import com.csp.spring.gateway.remote.model.OpenApiAuthReq;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@Component
public class RemoteHelper {

    private final MicroAuthReactApi microAuthReactApi;

    public RemoteHelper(MicroAuthReactApi microAuthReactApi) {
        this.microAuthReactApi = microAuthReactApi;
    }

    public Mono<CustomResponse> authOpenApi(OpenApiAuthReq authReq) {
        return microAuthReactApi.authOpenApi(authReq);
    }
}
