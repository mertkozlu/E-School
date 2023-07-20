package com.ESchool.dto.requests;

import lombok.Data;

@Data
public class AddStudentRequest {
    private Long studentNumber;
    private String studentName;
}
