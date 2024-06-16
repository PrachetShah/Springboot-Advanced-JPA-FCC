package com.example.apis.student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class School {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    public School(){
    }

    public School(String name) {
        this.name = name;
    }

    public School(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Store [id=" + id + ", name=" + name + "]";
    }

}
