package com.ESchool.controllers;

import com.ESchool.dtos.requests.AddStudentLessonRequest;
import com.ESchool.dtos.requests.UpdateStudentLessonRequest;
import com.ESchool.dtos.responses.GetAllStudentLessonResponse;
import com.ESchool.dtos.responses.GetStudentLessonByIdResponse;
import com.ESchool.entities.StudentLesson;
import com.ESchool.service.StudentLessonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentLessons")
public class StudentLessonController {
    private final StudentLessonService studentLessonService;

    public StudentLessonController(StudentLessonService studentLessonService) {
        this.studentLessonService = studentLessonService;
    }

    @GetMapping("/getAll")
    public GetAllStudentLessonResponse getAll() {
        return studentLessonService.getAllStudentLessons();
    }

    @PostMapping("/add")
    public StudentLesson addStudentLesson(@RequestBody AddStudentLessonRequest newStudentLesson) {
        return studentLessonService.addStudentLesson(newStudentLesson);
    }

    @GetMapping("/getById/{studentLessonId}")
    public GetStudentLessonByIdResponse getStudentLessonById(@PathVariable Long studentLessonId) {
        return studentLessonService.getStudentLessonById(studentLessonId);
    }

    @PutMapping("/update/{studentLessonId}")
    public StudentLesson updateStudentLesson(@RequestBody UpdateStudentLessonRequest updateStudentLessonRequest) {
        return studentLessonService.updateStudentLesson(updateStudentLessonRequest);
    }

    @DeleteMapping("/delete/{studentLessonId}")
    public void deleteStudentLesson(@PathVariable Long studentLessonId) {
        this.studentLessonService.deleteStudentLesson(studentLessonId);
    }
}
