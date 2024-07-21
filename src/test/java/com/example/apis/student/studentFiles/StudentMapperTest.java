package com.example.apis.student.studentFiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    // now this test will gave an error, hence we will have to go back to student mapper and handle this case
    @Test
    public void should_throw_nullPointerException_when_studentDto_is_null(){
        // first arg is exception.class, and second arg is lambda which returns error
        var exp = assertThrows(NullPointerException.class, ()->mapper.toStudent(null));
        assertEquals("DTO value cannot be null, pass a valid format", exp.getMessage());
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
