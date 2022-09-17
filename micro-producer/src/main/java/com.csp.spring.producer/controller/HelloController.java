package com.csp.spring.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CSP
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/v1")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<Object> hello(@RequestParam String name) {
        return ResponseEntity.ok("Hello " + name);
    }
}
