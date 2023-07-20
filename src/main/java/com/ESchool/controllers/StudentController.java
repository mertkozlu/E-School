package com.ESchool.controllers;

import com.ESchool.dto.responses.GetAllStudentResponse;
import com.ESchool.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/getAll")
    public GetAllStudentResponse getAll() {
        return studentService.getAllStudents();
    }
}
