package org.example.student.repository;

import jdk.jfr.Registered;
import org.example.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Registered
public interface StudentRepository extends JpaRepository<Student,Long> {

    Optional<Student> findByEmail(String email);
}
