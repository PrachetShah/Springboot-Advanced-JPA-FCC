package com.example.apis.student.studentFiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentMapperTest {
    private StudentMapper mapper;
    
    @BeforeEach
    void setUp() {
        mapper = new StudentMapper();
    }



    @Test
    public void shouldMapStudentDtoToStudent(){
        StudentDto dto = new StudentDto("Prachet","Does", "john@emaik.com", 1);
        Student student = mapper.toStudent(dto);

        // asserting if it is correct
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto(){
        // given
        Student student = new Student("Prachet", "Shah", "prachet@gmail.com", 21);

        // when we map given to 
        StudentResponseDto dto = mapper.toStudentResponseDto(student);

        // then we assert for these results, if it is correct
        assertEquals(dto.firstName(), student.getFirstName());
        assertEquals(dto.lastName(), student.getLastName());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getAge());
    }
}
