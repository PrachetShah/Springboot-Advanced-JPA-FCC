package com.example.apis.student;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class StudentProfile {
    @Id
    @GeneratedValue
    private int id;
    
    private String bio;

    // StudentProfile is Secondary Entity which is related by Student
    // to create relationship between tables we need to create a relationship type object
    @OneToOne
    @JoinColumn(
        // created foreign key with name student_id to join tables based on datatype in Student.java which is Integer
        name = "student_id"
    )
    // the name here should be name as "mappedBy" in Student.java which is student
    private Student student;

    public StudentProfile() {
    }

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "StoreProfile [id=" + id + ", bio=" + bio + "]";
    }

}
