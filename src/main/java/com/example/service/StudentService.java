package com.example.service;

import com.example.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
    Page<Student> getAllStudents(Pageable pageable);
    Student getStudentById(Long id);
}
