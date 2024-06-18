package com.example.apis;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FccCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FccCourseApplication.class, args);
	}

	@GetMapping()
    public String hello(){
        return "<h2>Got to /api/orders for order project, and /api/student for student project</h2>";
    }

}
