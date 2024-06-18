package com.orderservice.demo.Services;

import com.dtos.demo.Events.OrderState;
import com.dtos.demo.Events.ProductEvent;
import com.dtos.demo.Events.ProductStockState;
import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Entities.Product;
import com.orderservice.demo.Proxies.Productproxy;
import com.orderservice.demo.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class OrderService {

    //Product proxy injection
    @Autowired
    private Productproxy productproxy;

    //Order repository injection
    @Autowired
    private OrderRepository orderRepository;

    //Inject the order publisher, which will publish the order event
    @Autowired
    private OrderPublisher orderPublisher;

    public Order saveOrderInDB(long prodId, int qnt){

        //Get the concerned product from the product service using OpenFeign (In order to get the product price)
        Product prod = productproxy.getProductByid(prodId);

        Order newOrder = new Order(
                prod.getPrice()*qnt,
                new Date(),
                OrderState.CREATED);

        // Save order in the database
        orderRepository.save(newOrder);
        // Publish the orderEvent to the product service
        orderPublisher.publishOrderEvent(newOrder, prodId, qnt);
        return newOrder;
    }

    public void updateOrder(ProductEvent prdct){

        Optional<Order> newOrder = orderRepository.findById(prdct.getOrderId());
        if(newOrder.isPresent()){
            OrderState newOrderState = prdct.getStockAvailability().equals(ProductStockState.AVAILABLE) ?
                                   OrderState.PROCESSING : OrderState.FAILED;

            newOrder.get().setOrederState(newOrderState);
            orderRepository.save(newOrder.get());

        }
    }


}
