package org.example.student.service;

import org.example.student.dto.StudentDTO;
import org.example.student.exception.InvalidStudentDataException;
import org.example.student.exception.StudentAlreadyExistsException;
import org.example.student.exception.StudentNotFoundException;
import org.example.student.mapper.StudentMapper;
import org.example.student.model.Student;
import org.example.student.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private StudentDTO studentDTO;

    private Student student;

    @BeforeEach
    public void setup() {
        studentDTO = StudentDTO.builder()
                .name("Anagha")
                .email("Anagha@gmail.com")
                .location("Pune")
                .contactNo("9876123456").build();

        student = StudentMapper.toEntity(studentDTO);
        student.setStudentId(1L);
    }


    @Test
    void test_createStudent_Success(){
        Mockito.when(studentRepository.findByEmail(studentDTO.getEmail())).thenReturn(Optional.empty());
        Mockito.when(studentRepository.save(ArgumentMatchers.any(Student.class))).thenReturn(student);
        StudentDTO savedStudent = studentService.createStudent(studentDTO);
        Assertions.assertNotNull(savedStudent);
        Assertions.assertEquals("Anagha", savedStudent.getName());
        Assertions.assertEquals(studentDTO.getEmail(), savedStudent.getEmail());

    }

    @Test
    void test_createStudent_ThrowsInvalidStudentDataException(){
        Assertions.assertThrows(InvalidStudentDataException.class, () -> studentService.createStudent(null));
    }

    @Test
    void test_createStudent_ThrowsStudentAlreadyExistsException(){
        Mockito.when(studentRepository.findByEmail(studentDTO.getEmail())).thenReturn(Optional.of(student));
        Assertions.assertThrows(StudentAlreadyExistsException.class,() -> studentService.createStudent(studentDTO));
    }

    @Test
    void getStudent_Success(){
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        StudentDTO foundStudent = studentService.getStudent(1L);
        Assertions.assertNotNull(foundStudent);
        Assertions.assertEquals("Anagha", foundStudent.getName());
    }

    @Test
    void getStudent_ThrowsStudentNotFoundException(){
        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(StudentNotFoundException.class, () -> studentService.getStudent(1L));
    }


}
