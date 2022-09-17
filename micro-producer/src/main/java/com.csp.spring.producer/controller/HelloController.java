package com.csp.spring.producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CSP
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/v1")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<Object> hello(@RequestParam String name) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("name", name);
        resMap.put("timestamp", System.currentTimeMillis());
        return ResponseEntity.ok(resMap);
    }
}
