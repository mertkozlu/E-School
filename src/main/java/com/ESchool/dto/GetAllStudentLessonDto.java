package com.ESchool.dto;

import lombok.Data;

@Data
public class GetAllStudentLessonDto {
    private Long studentLessonId;
    private Long pointOne;
    private Long pointTwo;
    private double grade;
    private Long discontinuity;
}
