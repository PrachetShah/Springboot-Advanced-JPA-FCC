package com.example.apis.student.schoolFiles;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SchoolController {
    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("api/schools")
    public School createSchool(@RequestBody School school){
        return schoolRepository.save(school);
    }

    @GetMapping("api/schools")
    public List<School> getSchools(){
        return schoolRepository.findAll();
    }
}
