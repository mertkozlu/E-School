package com.ESchool.dtos.responses;

import lombok.Data;

@Data
public class GetAllLessonResponse {
    private Long lessonId;
    private String lessonName;
}
