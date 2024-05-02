package com.example.order.order;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderRepository orderRepo;
    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @GetMapping
    public List<Order> hello(){
        return orderRepo.findAll();
    }

    @PostMapping
    public Order add(@RequestBody Order order){
        return orderRepo.save(order);
    }

    @GetMapping(path="api/orders")
    public List<String> hi(){
        return List.of("Prachet", "Kevin");
    }
}
