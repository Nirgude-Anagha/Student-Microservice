package org.example.student.controller;

import org.example.student.dto.StudentDTO;
import org.example.student.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private StudentDTO studentDTO;

    @BeforeEach
    void setup(){
        studentDTO = StudentDTO.builder()
                .id(1L)
                .name("Anagha")
                .email("Anagha@gmail.com")
                .location("Pune")
                .contactNo("9876123456").build();
    }

    @Test
    void createStudent_ReturnsCreated(){
        Mockito.when(studentService.createStudent(ArgumentMatchers.any(StudentDTO.class))).thenReturn(studentDTO);
        ResponseEntity<StudentDTO> response = studentController.createStudent(studentDTO);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertEquals(studentDTO.getEmail(), response.getBody().getEmail());
    }

    @Test
    void getStudent_ReturnsOk(){
        Mockito.when(studentService.getStudent(1L)).thenReturn(studentDTO);
        ResponseEntity<StudentDTO> response = studentController.getStudent(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(studentDTO.getEmail(), response.getBody().getEmail());
    }
}
