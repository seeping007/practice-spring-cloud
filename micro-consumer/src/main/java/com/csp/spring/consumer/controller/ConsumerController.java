package com.csp.spring.consumer.controller;

import com.csp.spring.consumer.remote.RemoteHelper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author CSP
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/v1")
public class ConsumerController {

    private final RemoteHelper remoteHelper;

    public ConsumerController(RemoteHelper remoteHelper) {
        this.remoteHelper = remoteHelper;
    }

    @GetMapping("/hello")
    @ResponseStatus(code = HttpStatus.OK)
    public Object hello(@RequestParam String name) {
        return remoteHelper.hello(name);
    }
}
