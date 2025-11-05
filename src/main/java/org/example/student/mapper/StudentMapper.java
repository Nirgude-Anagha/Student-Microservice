package org.example.student.mapper;

import org.example.student.dto.StudentDTO;
import org.example.student.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public static Student toEntity(StudentDTO studentDTO) {
        if(studentDTO==null)
            return null;
        return Student.builder().name(studentDTO.getName())
                .email(studentDTO.getEmail())
                .contactNo(studentDTO.getContactNo())
                .location(studentDTO.getLocation())
                .build();
    }

    public static StudentDTO toDTO(Student student) {
        if(student==null)
            return null;
        return StudentDTO.builder().id(student.getStudentId()).
                name(student.getName())
                .email(student.getEmail())
                .contactNo(student.getContactNo())
                .location(student.getLocation()).build();
    }
}
