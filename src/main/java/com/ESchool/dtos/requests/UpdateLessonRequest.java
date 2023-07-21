package com.ESchool.dtos.requests;

import lombok.Data;

@Data
public class UpdateLessonRequest {
    private Long lessonId;
    private String lessonName;
}
