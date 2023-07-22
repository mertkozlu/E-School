package com.ESchool.dtos;

import lombok.Data;

@Data
public class GetStudentLessonByIdDto {
    private Long studentLessonId;
    private Long studentId;
    private String studentName;
    private Long lessonId;
    private String lessonName;
    private Long pointOne;
    private Long pointTwo;
    private double grade;
    private Long discontinuity;
}
