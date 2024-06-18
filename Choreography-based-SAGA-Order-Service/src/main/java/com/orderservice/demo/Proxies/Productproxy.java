package com.orderservice.demo.Proxies;

import com.orderservice.demo.Entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
* OpenFeign product proxy
* */

@FeignClient(name = "product-service")
public interface Productproxy {

    @GetMapping("/{prodId}")
    public Product getProductByid(@PathVariable long prodId);

}
