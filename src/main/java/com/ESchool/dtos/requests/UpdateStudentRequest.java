package com.ESchool.dtos.requests;

import lombok.Data;

@Data
public class UpdateStudentRequest {
    private Long studentId;
    private Long studentNumber;
    private String studentName;
}
