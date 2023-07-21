package com.ESchool.dtos;

import lombok.Data;

@Data
public class GetStudentByIdDto {
    private Long studentId;
    private Long studentNumber;
    private String studentName;
}
