package com.orderservice.demo.Initiator;

import com.dtos.demo.Events.OrderState;
import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import java.util.Date;

/*
* Code to run when launching the application for the first time
* */
@Component
public class DataInitiator implements
        ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        /*
        * If spring and springMVC are integrated in the web project, there will be two containers in the context,
        *  namely the parent container of spring applicationContext.xml and the child container of springMVC
        * applicationContext-mvc.xml . The two containers have the same life cycle, so the same event will be
        * sent once during the startup of different containers.
        * */
        if(event.getApplicationContext().getParent() == null) {

            orderRepository.save(new Order(160, new Date(), OrderState.CREATED));

            orderRepository.save(new Order(360, new Date(), OrderState.CREATED));

            orderRepository.save(new Order(5500, new Date(), OrderState.CREATED));
        }
    }
}
