package com.example.apis.order;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderRepository orderRepo;
    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }

    @PostMapping("api/orders")
    public Order add(@RequestBody Order order){
        Order exists = orderRepo.findByItemId(order.getItemId());
        if(exists == null){
            return orderRepo.save(order);
        }else{
            System.out.println("Order With ItemId exists");
            return null;
        }
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

    // delete using order id
    @DeleteMapping(path = "api/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteItem(@PathVariable int id){
        orderRepo.deleteById(id);
        return "delete successfully";
    }
}
