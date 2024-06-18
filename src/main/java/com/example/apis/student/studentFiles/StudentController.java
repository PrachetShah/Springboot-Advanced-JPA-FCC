package com.example.apis.student.studentFiles;

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
public class StudentController {
    private final StudentRepository studentRepository;
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping("api/students")
    public Student addStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    @GetMapping("api/students")
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping(path="api/students/{id}")
    public Student getStudentbyId(@PathVariable int id){
        return studentRepository.findById(id).orElse(null);
    }

    @GetMapping(path="api/students/search/{student_name}")
    public List<Student> getStudentbyName(@PathVariable("student_name") String student_name){
        return studentRepository.findAllByFirstNameContaining(student_name);
    }

    // delete using order id
    @DeleteMapping(path = "api/students/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable int id){
        studentRepository.deleteById(id);
        return "delete successfully";
    }
}
