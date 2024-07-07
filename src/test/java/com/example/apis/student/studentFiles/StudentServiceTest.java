package com.example.apis.student.studentFiles;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentServiceTest {

    /*
     * @BeforeEach and @BeforeAll are the JUnit 5 equivalents of @Before and @BeforeClass present in JUnit 4. 
     * These annotations were renamed with clearer names to avoid confusion.
     * 
     * Methods annotated with the @Before annotation are run before each test.
     */
    @BeforeEach
    void setUp() {
        System.out.println("Inside the before each set method");
    }

    /*
     * It works after each execution of tests
     * Methods annotated with the @After annotation are run after each test.
     */
    @AfterEach
    void tearDown() {
        System.out.println("Inside the after each set method");
    }

    @Test
    public void testMethod1() {
        System.out.println("My first test method");
    }

    @Test
    public void testMethod2() {
        System.out.println("My second test method");
    }
}
