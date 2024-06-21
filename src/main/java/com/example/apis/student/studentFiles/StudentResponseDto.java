package com.example.apis.student.studentFiles;

// new representation type to show student entity as following variables
public record StudentResponseDto(
    String firstName,
    String lastName,
    String email
  ) {
    
}
