package com.example.apis.student.studentFiles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
    @NotEmpty(message = "Firstname should not be empty")
    String firstName,
    @NotEmpty
    String lastName,
    @Email
    String email,
    Integer schoolId
) {

}
