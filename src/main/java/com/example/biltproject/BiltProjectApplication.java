package com.example.biltproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BiltProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiltProjectApplication.class, args);
    }

}
