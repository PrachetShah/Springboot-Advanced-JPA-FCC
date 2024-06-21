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

import com.example.apis.student.schoolFiles.School;


@RestController
@RequestMapping("api/students")
public class StudentController {
    private final StudentRepository studentRepository;
    public StudentController(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    // using DTO to hide sensitive information
    @PostMapping(path = "using_dto")
    public StudentResponseDto addStudentDto(@RequestBody StudentDto dto){
        // now this will give error as our repo can only save student type, so we convert dto into student with less args
        // return studentRepository.save(student);

        var student = toStudent(dto);
        // this way we have a new response which can be sent to user or a new representation of student
        var studentSaved = studentRepository.save(student);
        return toStudentResponseDto(studentSaved);
    }

    private StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    private Student toStudent(StudentDto dto){
        var student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());

        // set school since dto has a school id
        var school = new School();
        school.setId(dto.schoolId());

        // add school in student
        student.setSchool(school);

        return student;
    }

    @GetMapping
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping(path="{id}")
    public Student getStudentbyId(@PathVariable int id){
        return studentRepository.findById(id).orElse(null);
    }

    @GetMapping(path="search/{student_name}")
    public List<Student> getStudentbyName(@PathVariable("student_name") String student_name){
        return studentRepository.findAllByFirstNameContaining(student_name);
    }

    // delete using order id
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteStudent(@PathVariable int id){
        boolean exists = studentRepository.existsById(id);
        if(exists){
            studentRepository.deleteById(id);
            return "delete successfully";
        }else{
            return "item not found";
        }
    }
}
