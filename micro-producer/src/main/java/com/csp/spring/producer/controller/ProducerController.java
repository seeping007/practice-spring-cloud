package com.csp.spring.producer.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.csp.spring.producer.config.ExtConfig;
import com.csp.spring.producer.config.MainConfig;
import com.csp.spring.producer.model.HelloVO;
import com.csp.spring.web.exception.ParamInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author CSP
 * @since 2019-12-25
 */
@RestController
@RequestMapping("/v1")
public class ProducerController {

    @NacosValue(value = "${main-config.desc}", autoRefreshed = true)
    private String mainConfigDesc;

    private final MainConfig mainConfig;

    private final ExtConfig extConfig;

    public ProducerController(MainConfig mainConfig, ExtConfig extConfig) {
        this.mainConfig = mainConfig;
        this.extConfig = extConfig;
    }

    @GetMapping("/hello")
    @ResponseStatus(code = HttpStatus.OK)
    public Object hello(@RequestParam String name) {

        if (!name.equals(mainConfig.getHelloPass())) {
            throw new ParamInvalidException("Invalid hello name");
        }

        HelloVO helloVO = HelloVO.builder()
                .name(name).timestamp(System.currentTimeMillis())
                .mainConfig(mainConfig.getDesc()).extConfig(extConfig.getDesc())
                .mainConfigDesc(mainConfigDesc).build();

        return helloVO;
    }
}
