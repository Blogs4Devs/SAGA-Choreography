package com.orderservice.demo.Repositories;

import com.orderservice.demo.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    /*
    * Here you can specify other JPA methods if needed
    * */
}

