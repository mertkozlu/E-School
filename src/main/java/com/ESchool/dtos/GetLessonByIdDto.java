package com.ESchool.dtos;

import lombok.Data;

@Data
public class GetLessonByIdDto {
    private Long lessonId;
    private String lessonName;
}
