package com.ESchool.controllers;

import com.ESchool.dtos.requests.AddStudentRequest;
import com.ESchool.dtos.responses.GetAllStudentResponse;
import com.ESchool.entities.Student;
import com.ESchool.service.StudentService;
import org.springframework.web.bind.annotation.*;

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
