package com.orderservice.demo.Config;

import com.dtos.demo.Events.ProductEvent;
import com.orderservice.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.function.Consumer;

@Configuration
public class ProductEventConsumerConfig {


    @Autowired
    private OrderService orderService;

    @Bean
    public Consumer<ProductEvent> productEventConsumer(){
        return (productEvn) -> orderService.updateOrder(productEvn);

    }
}
