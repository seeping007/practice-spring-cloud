package com.csp.spring.consumer.remote;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.csp.spring.web.exception.BusinessException;
import com.csp.spring.web.exception.FeignBusinessException;
import com.csp.spring.web.model.CustomResponse;
import com.csp.spring.web.model.ErrorModel;
import com.csp.spring.web.model.ErrorResponse;
import feign.Response;
import feign.Util;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.spring.SpringFormEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author chensiping
 * @since 2021-03-08
 */
@Slf4j
@Configuration
public class CustomFeignConfiguration {

    // For: application/x-www-form-urlencoded body
    @Bean
    Encoder feignFormEncoder(ObjectFactory<HttpMessageConverters> converters) {
        return new SpringFormEncoder(new SpringEncoder(converters));
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new UserErrorDecoder();
    }

    /**
     * 被调用方的业务异常（非200），直接上抛由上层处理，不进入熔断
     */
    static class UserErrorDecoder implements ErrorDecoder {

        @Override
        public Exception decode(String s, Response response) {

            String responseStr;
            try {
                responseStr = Util.toString(response.body().asReader(StandardCharsets.UTF_8));
            } catch (IOException e) {
                log.error("", e);
                throw new RuntimeException(e.getMessage());
            }

            JSONObject respJson = JSON.parseObject(responseStr);
            if (respJson.containsKey("error")) {
                // body format: {"error": {"type": "", "message": ""}}
                ErrorResponse errorResponse = JSON.parseObject(responseStr, ErrorResponse.class);
                ErrorModel errorModel = errorResponse.getError();
                return new FeignBusinessException(
                        response.status(), errorModel.getType(), errorModel.getMessage());
            } else if (respJson.containsKey("status")) {
                // body format: {"status": "", "message": "", data: {}}
                CustomResponse resp = JSON.parseObject(responseStr, CustomResponse.class);
                return new FeignBusinessException(
                        response.status(), resp.getStatus(), resp.getMessage());
            } else {
                log.error("[feign] error body: {}", responseStr);
                throw new BusinessException("REMOTE_ERROR", "Unknown feign error body");
            }
        }
    }
}
