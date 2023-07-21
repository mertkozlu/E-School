package com.ESchool.dtos;

import lombok.Data;

@Data
public class GetAllStudentLessonDto {
    private Long studentId;
    private String studentName;
    private Long lessonId;
    private String lessonName;
    private Long studentLessonId;
    private Long pointOne;
    private Long pointTwo;
    private double grade;
    private Long discontinuity;
}
