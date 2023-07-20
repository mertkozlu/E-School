package com.ESchool.dto;

import lombok.Data;

@Data
public class GetAllStudentDto {
    private Long studentId;
    private Long studentNumber;
    private String studentName;
    private Long password;
}
