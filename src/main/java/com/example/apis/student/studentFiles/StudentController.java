package com.example.apis.student.studentFiles;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/students")
public class StudentController {
    // dependency injection
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentService.addStud(student);
    }

    // using DTO to hide sensitive information
    @PostMapping(path = "using_dto")
    public StudentResponseDto addStudentDto(@RequestBody StudentDto dto){
        return studentService.addStudDto(dto);
    }

    @GetMapping
    public List<Student> getAll(){
        return studentService.getStudents();
    }

    @GetMapping(path="using_dto")
    public List<StudentResponseDto> getAllDto(){
        return studentService.getStudentsDto();
    }

    @GetMapping(path="{id}")
    public StudentResponseDto getStudentbyId(@PathVariable int id){
        return studentService.getStudentById(id);
    }

    @GetMapping(path="search/{student_name}")
    public List<StudentResponseDto> getStudentbyName(@PathVariable("student_name") String student_name){
        return studentService.getByName(student_name);
    }

    // delete using id
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable int id){
        return studentService.deleteStudent(id);
    }
}
