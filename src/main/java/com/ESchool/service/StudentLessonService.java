package com.ESchool.service;

import com.ESchool.dataAccess.StudentLessonRepository;

public class StudentLessonService {
    private final StudentLessonRepository studentLessonRepository;

    public StudentLessonService(StudentLessonRepository studentLessonRepository) {
        this.studentLessonRepository = studentLessonRepository;
    }
}
