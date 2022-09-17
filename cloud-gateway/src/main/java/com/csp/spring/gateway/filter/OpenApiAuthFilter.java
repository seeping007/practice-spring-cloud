package com.csp.spring.gateway.filter;

import com.csp.spring.gateway.model.CustomHeaders;
import com.csp.spring.gateway.remote.RemoteHelper;
import com.csp.spring.gateway.remote.model.OpenApiAuthReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@Slf4j
@Component
public class OpenApiAuthFilter implements GlobalFilter, Ordered {

    private final RemoteHelper remoteHelper;

    public OpenApiAuthFilter(RemoteHelper remoteHelper) {
        this.remoteHelper = remoteHelper;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath();

        HttpHeaders headers = request.getHeaders();
        String appKey = headers.getFirst(CustomHeaders.APP_KEY);
        if (StringUtils.isEmpty(appKey)) {
            log.warn("Invalid appKey: uri = {}", uri);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "App-Key is required");
        }

        OpenApiAuthReq authReq = OpenApiAuthReq.builder().appKey(appKey).build();
        return remoteHelper.authOpenApi(authReq)
                .doOnSuccess(customResponse -> {

                    if ("UNAUTHORIZED".equals(customResponse.getStatus())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid App-Key");
                    }

                })
                .flatMap(customResponse -> {

                    Consumer<HttpHeaders> httpHeaders = httpHeader -> {

                        httpHeader.set(CustomHeaders.APP_KEY, appKey);
                    };

                    ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
                    return chain.filter(exchange.mutate().request(serverHttpRequest).build());
                });
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
