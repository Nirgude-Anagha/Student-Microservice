package org.example.student.exception;

public class InvalidStudentDataException extends RuntimeException{

    public InvalidStudentDataException(String message) {
        super(message);
    }
}
