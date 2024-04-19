package com.csp.cloud.user.controller;


import com.csp.cloud.infra.web.aspects.UserContextHolder;
import com.csp.cloud.user.model.entity.Tourist;
import com.csp.cloud.user.service.TouristService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 游客表 前端控制器
 * </p>
 *
 * @author chensiping
 * @since 2024-04-19
 */
@RestController
@RequestMapping("/v1/tourist")
public class TouristController {

    @Autowired
    private UserContextHolder userContextHolder;

    @Autowired
    private TouristService touristService;

    @GetMapping("/me")
    public Object me() {
        String userId = userContextHolder.getUserId();
        Tourist tourist = touristService.lambdaQuery().eq(Tourist::getUserId, userId).one();
        return tourist;
    }
}
