package com.ESchool.controllers;

import com.ESchool.dtos.requests.AddLessonRequest;
import com.ESchool.dtos.requests.UpdateLessonRequest;
import com.ESchool.dtos.responses.GetAllLessonResponse;
import com.ESchool.dtos.responses.GetLessonByIdResponse;
import com.ESchool.entities.Lesson;
import com.ESchool.result.DataResult;
import com.ESchool.result.Result;
import com.ESchool.service.LessonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/getAll")
    public DataResult<List<GetAllLessonResponse>> getAll() {
        return lessonService.getAllLessons();
    }

    @PostMapping("/add")
    public Result addLesson(@RequestBody AddLessonRequest newLesson) {
        return lessonService.addLesson(newLesson);
    }

    @GetMapping("/getById/{lessonId}")
    public GetLessonByIdResponse getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @PutMapping("/update/{lessonId}")
    public Lesson updateLesson(@RequestBody UpdateLessonRequest updateLessonRequest) {
        return lessonService.updateLesson(updateLessonRequest);
    }

    @DeleteMapping("/delete/{lessonId}")
    public void deleteLesson(@PathVariable Long lessonId) {
        this.lessonService.deleteLesson(lessonId);
    }


}
