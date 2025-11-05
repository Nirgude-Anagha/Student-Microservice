package org.example.student.repository;

import org.example.student.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void test_save(){
        Student student = new Student();
        student.setName("Anagha");
        student.setContactNo("9623123456");
        student.setLocation("Pune");
        student.setEmail("Anagha@gmail.com");
        studentRepository.save(student);
    }

    @Test
    void test_findByEmail(){
        Student student = new Student();
        student.setName("Anagha");
        student.setContactNo("9623123456");
        student.setLocation("Pune");
        student.setEmail("Anagha@gmail.com");
        studentRepository.save(student);

        Optional<Student> student1 = studentRepository.findByEmail(student.getEmail());
        Assertions.assertTrue(student1.isPresent());
        Assertions.assertEquals(student.getEmail(),student1.get().getEmail());
        Assertions.assertEquals(student.getContactNo(),student1.get().getContactNo());
    }
}
