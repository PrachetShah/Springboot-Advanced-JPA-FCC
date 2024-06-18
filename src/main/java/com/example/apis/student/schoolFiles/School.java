package com.example.apis.student.schoolFiles;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class School {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    // relationship b/w school and students is One To Many
    @OneToMany(
        mappedBy = "school"
    )
    private List<Student> students;

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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Store [id=" + id + ", name=" + name + "]";
    }

}
