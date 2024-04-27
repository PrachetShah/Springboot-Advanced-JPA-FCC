package com.example.order;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Order {
    private Integer id;
    private String item;
    private String seller;
    private String category;
    private Integer quantity;

    // Constructors
    public Order(Integer id, String item, String seller, String category, Integer quantity) {
        this.id = id;
        this.item = item;
        this.seller = seller;
        this.category = category;
        this.quantity = quantity;
    }
    public Order(String item, String seller, String category, Integer quantity) {
        this.item = item;
        this.seller = seller;
        this.category = category;
        this.quantity = quantity;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", item=" + item + ", seller=" + seller + ", category=" + category + ", quantity="
                + quantity + "]";
    }
}
