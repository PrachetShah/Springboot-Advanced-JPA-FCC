package com.example.apis.student.schoolFiles;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    // dependency injection
    private final SchoolRepository schoolRepository;
    
    public SchoolService(SchoolRepository schoolRepository){
        this.schoolRepository = schoolRepository;
    }

    //add school
    public School addSchool(School school){
        return schoolRepository.save(school);
    }

    // add schoolDto
    public SchoolDto addSchoolDto(SchoolDto dto){
        var school = toSchool(dto);
        schoolRepository.save(school);
        // here the output is same as input i.e dto, hence we return dto. whereas in Student, we had different 
        // Output(StudentResponseDto) and Input(StudentDto)
        return dto;
    }
    // convert schoolDto to type School, internal func hence private
    private School toSchool(SchoolDto dto){
        return new School(dto.schoolName()); 
    }

    // get all schools list
    public List<School> getSchools(){
        return schoolRepository.findAll();
    }

    // get all schooldtos
    public List<SchoolDto> geDtos(){
         // converting stream of School List into SchoolDto using a mapper function
        return schoolRepository.findAll()
        .stream()
        .map(this::toSchoolDto)
        .collect(Collectors.toList());
    }
    public SchoolDto toSchoolDto(School school){
        return new SchoolDto(school.getname());
    }
}
