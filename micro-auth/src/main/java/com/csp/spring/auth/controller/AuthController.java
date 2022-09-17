package com.csp.spring.auth.controller;

import com.csp.spring.auth.config.AppConfig;
import com.csp.spring.auth.config.AppInfoConfig;
import com.csp.spring.auth.model.CustomResponse;
import com.csp.spring.auth.model.OpenApiAuthReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@RestController
@RequestMapping("/v1")
public class AuthController {

    private final AppInfoConfig appInfoConfig;

    public AuthController(AppInfoConfig appInfoConfig) {
        this.appInfoConfig = appInfoConfig;
    }

    @PostMapping("/openapi/auth")
    public ResponseEntity<Object> authOpenApi(@RequestBody OpenApiAuthReq req) {

        CustomResponse response = CustomResponse.builder()
                .status("SUCCESS").build();

        AppConfig appConfig = appInfoConfig.getAppConfig(req.getAppKey());
        if (appConfig == null) {
            response.setStatus("UNAUTHORIZED");
        }

        return ResponseEntity.ok(response);
    }
}
