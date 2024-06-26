package com.example.apis.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
    /*
     * To find the conevention of writing it see online, it follows
     * "findAllBy---" or "findBy---" based on what to get
     * with --- replaced with "NAME OF VARIABLE in Table in DB"
     * To see more methods: https://docs.spring.io/spring-data/jpa/reference/repositories/query-methods-details.html
     */
    // to find from item name
    List<Order> findAllByItem(String item);
    Order findByItemId(int id);
}
