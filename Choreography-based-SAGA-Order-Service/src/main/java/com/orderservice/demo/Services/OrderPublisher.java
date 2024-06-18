package com.orderservice.demo.Services;

import com.dtos.demo.Events.OrderEvent;
import com.orderservice.demo.Entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Service
public class OrderPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    //Publish the order event
    public void publishOrderEvent(Order newOrder, long prodId, int qnt){
        // Create an order event with the necessary data and send it.
        OrderEvent orderEvent = new OrderEvent(newOrder.getId(), prodId, qnt);
        orderSinks.tryEmitNext(orderEvent);
    }
}
