package com.example.apis.student.studentFiles;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentServiceTest {

    // which service we want to test?
    @InjectMocks
    private StudentService studentService;

    //declare the dependencies, to run the code in isolation, we need to mock studentRepo and studentMap
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
}
