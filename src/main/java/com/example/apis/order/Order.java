package com.example.apis.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String item;
    @Column(unique = true)
    private int itemId;
    private String seller;
    private String category;
    private int quantity;

    // Constructors
    public Order() {
    }
    public Order(int id, String item, String seller, String category, int quantity) {
        this.id = id;
        this.item = item;
        this.seller = seller;
        this.category = category;
        this.quantity = quantity;
    }
    public Order(String item, String seller, String category, int quantity) {
        this.item = item;
        this.seller = seller;
        this.category = category;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public int getItemId() {
        return itemId;
    }
    public void setItemId(int itemId) {
        this.itemId = itemId;
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
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", item=" + item + ", seller=" + seller + ", category=" + category + ", quantity="
                + quantity + "]";
    }
}
