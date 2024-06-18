package com.example.apis.student.studentFiles;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findStudentByEmail(String email);
    List<Student> findAllByFirstNameContaining(String name);
}
