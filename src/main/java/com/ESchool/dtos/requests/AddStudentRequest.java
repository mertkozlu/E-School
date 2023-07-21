package com.ESchool.dtos.requests;

import lombok.Data;

@Data
public class AddStudentRequest {
    private Long studentNumber;
    private String studentName;
}
