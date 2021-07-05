package com.example.controller;

import com.example.model.Student;
import com.example.resource.SaveStudentResource;
import com.example.resource.StudentResource;
import com.example.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class StudentController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudentService studentService;


    @GetMapping("/students")
    public Page<StudentResource> getAllStudents(Pageable pageable){
        Page<Student> studentPage = studentService.getAllStudents(pageable);
        List<StudentResource> resources = studentPage.getContent().stream().map(this::convertToResource).
                collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/students/{studentId}")
    public StudentResource getStudentById(@PathVariable(name = "studentId") Long studentId){
        return convertToResource(studentService.getStudentById(studentId));
    }

    private Student convertToEntity(SaveStudentResource resource){
        return mapper.map(resource, Student.class);
    }

    private StudentResource convertToResource(Student entity){
        return mapper.map(entity, StudentResource.class);
    }
}
