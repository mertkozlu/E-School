package com.ESchool.controllers;

import com.ESchool.dtos.requests.AddStudentLessonRequest;
import com.ESchool.dtos.responses.GetAllStudentLessonResponse;
import com.ESchool.entities.StudentLesson;
import com.ESchool.service.StudentLessonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("studentLessons")
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
}
