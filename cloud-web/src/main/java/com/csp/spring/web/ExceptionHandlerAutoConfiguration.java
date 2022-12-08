package com.csp.spring.web;

import com.csp.spring.web.handler.CustomExceptionHandler;
import com.csp.spring.web.handler.GlobalExceptionHandler;
import com.csp.spring.web.handler.GlobalExceptionTranslator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author chensiping
 * @since 2022-12-06
 */
@Configuration
public class ExceptionHandlerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionTranslator.class)
    public GlobalExceptionTranslator defaultGlobalExceptionTranslator() {
        return new GlobalExceptionTranslator() {

            @Override
            protected int serviceCode() {
                return 0;
            }
        };
    }

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler(GlobalExceptionTranslator globalExceptionTranslator,
                                                         List<CustomExceptionHandler> customExceptionHandlers) {
        return new GlobalExceptionHandler(globalExceptionTranslator, customExceptionHandlers);
    }
}
