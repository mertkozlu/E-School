package com.ESchool.dto.requests;

import lombok.Data;

@Data
public class AddStudentLessonRequest {
    private Long pointOne;
    private Long pointTwo;
    private double grade;
    private Long discontinuity;
}
