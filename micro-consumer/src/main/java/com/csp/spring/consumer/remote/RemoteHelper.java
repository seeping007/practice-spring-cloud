package com.csp.spring.consumer.remote;

import com.csp.spring.consumer.remote.model.HelloVO;
import org.springframework.stereotype.Component;

/**
 * @author chensiping
 * @since 2022-12-06
 */
@Component
public class RemoteHelper {

    private final MicroProducerClient microProducerClient;

    public RemoteHelper(MicroProducerClient microProducerClient) {
        this.microProducerClient = microProducerClient;
    }

    public HelloVO hello(String name) {

        return microProducerClient.hello(name);
    }
}
