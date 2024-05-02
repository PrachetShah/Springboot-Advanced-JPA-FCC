package com.example.order.order;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderRepository orderRepo;
    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @PostMapping
    public Order add(@RequestBody Order order){
        return orderRepo.save(order);
    }

    @GetMapping("api/orders")
    public List<Order> hello(){
        return orderRepo.findAll();
    }

    @GetMapping(path="api/orders/{id}")
    public Order getOrderbyId(@PathVariable int id){
        return orderRepo.findById(id).orElse(null);
    }

    // to find from item name
    @GetMapping(path="api/orders/items/{item_name}")
    public List<Order> getItemsbyName(@PathVariable("item_name") String name){
        return orderRepo.findAllByItem(name);
    }

    // to find from item id
    @GetMapping(path="api/orders/itemsid/{item_id}")
    public Order getItemsbyName(@PathVariable("item_id") int itemId){
        return orderRepo.findByItemId(itemId);
    }
}
