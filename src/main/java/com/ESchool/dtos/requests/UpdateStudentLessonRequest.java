package com.ESchool.dtos.requests;

import lombok.Data;

@Data
public class UpdateStudentLessonRequest {
    private Long studentLessonId;
    private Long studentId;
    private Long lessonId;
    private Long pointOne;
    private Long pointTwo;
    private double grade;
    private Long discontinuity;
}
