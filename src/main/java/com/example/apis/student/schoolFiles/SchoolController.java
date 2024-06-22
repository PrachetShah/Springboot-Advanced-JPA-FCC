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
    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService){
        this.schoolService = schoolService;
    }

    @PostMapping
    public School createSchool(@RequestBody School school){
        return schoolService.addSchool(school);
    }

    // using School DTO
    @PostMapping(path = "using_dto")
    public SchoolDto createSchoolDto(@RequestBody SchoolDto dto){
        return schoolService.addSchoolDto(dto);
    }

    // get all schools with their students, students here are full student and not studentDto
    @GetMapping
    public List<School> get(){
        return schoolService.getSchools();
    }

    // get all schoolsDto
    @GetMapping(path = "using_dto")
    public List<SchoolDto> getSchoolDto(){
        return schoolService.geDtos();
    }
}
