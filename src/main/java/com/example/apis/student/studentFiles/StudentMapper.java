package com.example.apis.student.studentFiles;

import org.springframework.stereotype.Service;

import com.example.apis.student.schoolFiles.School;

@Service
public class StudentMapper {
    // change Student type to StudenDto type, internal private func
    public StudentResponseDto toStudentResponseDto(Student student){
        return new StudentResponseDto(student.getFirstName(), student.getLastName(), student.getEmail());
    }

    // change StudentDto type to Student type, internal private func
    public Student toStudent(StudentDto dto){
        // check for null values
        if(dto == null){
            throw new NullPointerException("DTO value cannot be null, pass a valid format");
        }
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
}
