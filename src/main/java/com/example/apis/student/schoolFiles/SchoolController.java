package com.example.apis.student.schoolFiles;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/schools")
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    @PostMapping
    public School createSchool(@RequestBody School school){
        return schoolRepository.save(school);
    }

    // using School DTO
    @PostMapping(path = "using_dto")
    public SchoolDto createSchoolDto(@RequestBody SchoolDto dto){
        // now this will give error as our repo can only save School type, so we convert dto into School with less args
        // return schoolRepository.save(school);

        var school = toSchool(dto);
        schoolRepository.save(school);
        // here the output is same as input i.e dto, hence we return dto. whereas in Student, we had different 
        // Output(StudentResponseDto) and Input(StudentDto)
        return dto;
    }

    private School toSchool(SchoolDto dto){
        return new School(dto.schoolName());
    }

    // get all schools with their students, students here are full student and not studentDto
    @GetMapping
    public List<School> getSchools(){
        return schoolRepository.findAll();
    }
}
