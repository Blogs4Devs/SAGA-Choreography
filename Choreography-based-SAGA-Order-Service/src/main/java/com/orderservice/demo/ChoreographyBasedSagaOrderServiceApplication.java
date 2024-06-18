package com.orderservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients // Enable OpenFeign to work
public class ChoreographyBasedSagaOrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoreographyBasedSagaOrderServiceApplication.class, args);
    }

}
