package com.ESchool.controllers;

import com.ESchool.dto.requests.AddStudentRequest;
import com.ESchool.dto.responses.GetAllStudentResponse;
import com.ESchool.entities.Student;
import com.ESchool.service.StudentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/add")
    public Student addStudent(@RequestBody AddStudentRequest newStudent) {
        return studentService.addStudent(newStudent);
    }
}
