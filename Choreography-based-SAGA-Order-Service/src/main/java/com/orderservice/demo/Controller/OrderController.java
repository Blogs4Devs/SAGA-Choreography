package com.orderservice.demo.Controller;

import com.orderservice.demo.Entities.Order;
import com.orderservice.demo.Repositories.OrderRepository;
import com.orderservice.demo.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    /*
    * Order Repository to manipulate the order database table
    * */
    @Autowired
    private OrderRepository orderRepository;

    /*
     * Order service implementation
     * */
    @Autowired
    private OrderService orderService;

    /*
    * Get the order list from the database
    * */
    @GetMapping("/AllOrders")
    public List<Order> getOrderList(){
        return orderRepository.findAll();
    }


    /*
    * Get information about a specific order
    * */
    @GetMapping("/{orderId}")
    public Order getOrderByid(@PathVariable long orderId){
        return orderRepository.findById(orderId).get();
    }


    /*
    * Create new order with CREATED state.
    * Check the product stock availability.
    * If the product is available, change the order state to PROCESSING.
    * If the product is out of stock we will delete the order.
    * */
    @GetMapping("/new/{prodId}/{qnt}")
    public Order createOrder(@PathVariable long prodId, @PathVariable int qnt){
        // saveOrder of the order service
        return orderService.saveOrderInDB(prodId, qnt);

    }

}
