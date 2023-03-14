package com.qx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qx.mapper")
public class SpringSecurityApplication {

    public static void main(String[] args) {
//        ConfigurableApplicationContext run = SpringApplication.run(SpringSecurityApplication.class);
//        System.out.println(run);
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
