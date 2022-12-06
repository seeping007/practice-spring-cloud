package com.csp.spring.consumer.remote;

import com.csp.spring.consumer.remote.model.HelloVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author chensiping
 * @since 2022-12-06
 */
@FeignClient(name = "micro-producer", url = "${remote-service.micro-producer.baseUrl}", // url: 有则使用，没有则使用name
        fallbackFactory = MicroProducerClientFallbackFactory.class,
        configuration = CustomFeignConfiguration.class)
public interface MicroProducerClient {

    @GetMapping("/v1/hello")
    HelloVO hello(@RequestParam("name") String name);
}
