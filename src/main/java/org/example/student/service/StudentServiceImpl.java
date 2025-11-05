package org.example.student.service;

import org.example.student.dto.StudentDTO;
import org.example.student.exception.InvalidStudentDataException;
import org.example.student.exception.StudentAlreadyExistsException;
import org.example.student.exception.StudentNotFoundException;
import org.example.student.mapper.StudentMapper;
import org.example.student.model.Student;
import org.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository  studentRepository;

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if(studentDTO == null){
            throw new InvalidStudentDataException("Student data cannot be null");
        }
        studentRepository.findByEmail(studentDTO.getEmail()).ifPresent(s -> {
            throw new StudentAlreadyExistsException("Student with email id " + studentDTO.getEmail() + " already exists");
        });
        Student student = StudentMapper.toEntity(studentDTO);
        return StudentMapper.toDTO(studentRepository.save(student));
    }

    @Override
    public StudentDTO getStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student with id " + id + " does not exist"));
        return StudentMapper.toDTO(student);
    }
}
