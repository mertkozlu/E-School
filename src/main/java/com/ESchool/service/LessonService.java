package com.ESchool.service;

import com.ESchool.dataAccess.LessonRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }
}
