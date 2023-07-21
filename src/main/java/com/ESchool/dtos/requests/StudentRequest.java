package com.ESchool.dtos.requests;

import lombok.Data;

@Data
public class StudentRequest {
    private String studentName;
    private Long studentNumber;
    private String password;
}
