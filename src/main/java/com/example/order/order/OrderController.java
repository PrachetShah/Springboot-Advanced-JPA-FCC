package com.example.order.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @GetMapping
    public String hello(){
        return "Hello World";
    }
}
