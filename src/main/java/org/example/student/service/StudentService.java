package org.example.student.service;

import org.example.student.dto.StudentDTO;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO);

    StudentDTO getStudent(Long id);
}
