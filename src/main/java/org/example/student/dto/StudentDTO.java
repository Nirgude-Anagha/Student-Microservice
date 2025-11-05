package org.example.student.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Please enter a valid email address")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Contact No is mandatory")
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Contact Number must be 10 digits and start with 6-9")
    private String contactNo;

    @NotBlank(message = "Location is mandatory")
    private String location;
}
