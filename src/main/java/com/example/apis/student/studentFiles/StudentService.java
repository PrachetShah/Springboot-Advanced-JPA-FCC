package com.example.apis.student.studentFiles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    // dependency Injection
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper){
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    /*
     * SERVICE Methods are public to allow Application Layer to Call them,
     * but internal methods are kept private i.e toStudent(), toStudentDto(), etc to 
     * encapsulate them from public
    */

    /* 
        Add students in repo
    */ 
    public Student addStud(Student student){
        return studentRepository.save(student);
    }

    // add student dto
    public StudentResponseDto addStudDto(StudentDto dto){
        var student = studentMapper.toStudent(dto);
        // this way we have a new response which can be sent to user or a new representation of student
        var studentSaved = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(studentSaved);
    }

    // get all students
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    // get all studentsResponse DTO
    public List<StudentResponseDto> getStudentsDto(){
        return studentRepository.findAll()
        .stream()
        .map(studentMapper::toStudentResponseDto)
        .collect(Collectors.toList());
    }

    // get studentRespDto by id
    public StudentResponseDto getStudentById(int id){
        return studentRepository.findById(id)
        .map(studentMapper::toStudentResponseDto)
        .orElse(null);
    }

    // search for student by name and output StudentResponseDto
    public List<StudentResponseDto> getByName(String name){
        return studentRepository.findAllByFirstNameContaining(name)
        .stream()
        .map(studentMapper::toStudentResponseDto)
        .collect(Collectors.toList());
    }

    // delete based on id
    public String deleteStudent(int id){
        boolean exists = studentRepository.existsById(id);
        if(exists){
            studentRepository.deleteById(id);
            return "delete successfully";
        }else{
            return "student with id:"+id+" not present";
        }
    }
}
