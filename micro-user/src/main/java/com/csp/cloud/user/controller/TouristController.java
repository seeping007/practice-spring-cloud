package com.csp.cloud.user.controller;

import com.csp.cloud.infra.core.exception.NotFoundException;
import com.csp.cloud.infra.web.aspects.UserContextHolder;
import com.csp.cloud.user.model.entity.Tourist;
import com.csp.cloud.user.service.TouristService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/v1/tourists")
public class TouristController {

    private final UserContextHolder userContextHolder;

    private final TouristService touristService;

    public TouristController(UserContextHolder userContextHolder, TouristService touristService) {
        this.userContextHolder = userContextHolder;
        this.touristService = touristService;
    }

    @GetMapping("/me")
    public Object me() {
        String userId = userContextHolder.getUserId();
        Tourist tourist = touristService.lambdaQuery().eq(Tourist::getUserId, userId).one();
        if (tourist == null) {
            throw new NotFoundException("Tourist not exist");
        }
        return tourist;
    }
}
