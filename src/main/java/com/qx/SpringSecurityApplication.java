package com.qx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@MapperScan("com.qx.mapper")
@EnableAsync //启动异步
public class SpringSecurityApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext run = SpringApplication.run(SpringSecurityApplication.class);
//        System.out.println(run);
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
