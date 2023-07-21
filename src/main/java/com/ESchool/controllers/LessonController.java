package com.ESchool.controllers;

import com.ESchool.dtos.requests.AddLessonRequest;
import com.ESchool.dtos.responses.GetAllLessonResponse;
import com.ESchool.entities.Lesson;
import com.ESchool.service.LessonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/getAll")
    public GetAllLessonResponse getAll() {
        return lessonService.getAllLessons();
    }

    @PostMapping("/add")
    public Lesson addLesson(@RequestBody AddLessonRequest newLesson) {
        return lessonService.addLesson(newLesson);
    }
}
