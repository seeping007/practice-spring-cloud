package com.csp.cloud.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chensiping
 * @since 2022-09-17
 */
@ComponentScan(basePackages = {"com.csp.cloud"})
@MapperScan(basePackages = {
        "com.csp.cloud.user.mapper",
})
@SpringBootApplication
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
