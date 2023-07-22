package com.ESchool.controllers;

import com.ESchool.dtos.requests.AddStudentRequest;
import com.ESchool.dtos.requests.UpdateStudentRequest;
import com.ESchool.dtos.responses.GetAllStudentResponse;
import com.ESchool.dtos.responses.GetStudentByIdResponse;
import com.ESchool.entities.Student;
import com.ESchool.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
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

    @GetMapping("/getById/{studentId}")
    public GetStudentByIdResponse getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/update/{studentId}")
    public void updateStudent(@RequestBody UpdateStudentRequest updateStudentRequest) {
        this.studentService.updateStudent(updateStudentRequest);
    }

    @DeleteMapping("/delete/{studentId}")
    public void deleteStudent(@PathVariable Long studentId) {
        this.studentService.deleteStudentById(studentId);
    }
}
