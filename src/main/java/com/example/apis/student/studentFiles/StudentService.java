package com.example.apis.student.studentFiles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.apis.student.schoolFiles.School;

@Service
public class StudentService {
    // dependency Injection
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
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
        var student = toStudent(dto);
        // this way we have a new response which can be sent to user or a new representation of student
        var studentSaved = studentRepository.save(student);
        return toStudentResponseDto(studentSaved);
    }

    // change Student type to StudenDto type, internal private func
    private StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    // change StudentDto type to Student type, internal private func
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

    // get all students
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    // get all studentsResponse DTO
    public List<StudentResponseDto> getStudentsDto(){
        return studentRepository.findAll()
        .stream()
        .map(this::toStudentResponseDto)
        .collect(Collectors.toList());
    }

    // get studentRespDto by id
    public StudentResponseDto getStudentById(int id){
        return studentRepository.findById(id)
        .map(this::toStudentResponseDto)
        .orElse(null);
    }

    // search for student by name and output StudentResponseDto
    public List<StudentResponseDto> getByName(String name){
        return studentRepository.findAllByFirstNameContaining(name)
        .stream()
        .map(this::toStudentResponseDto)
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
