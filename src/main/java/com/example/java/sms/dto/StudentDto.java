package com.example.java.sms.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    @NotEmpty(message = "student first name should not be empty")
    private String firstName;
    @NotEmpty(message = "student last name should not be empty")
    private String lastName;
    @NotEmpty(message = "student mail id should not be empty")
    private String email;


}
