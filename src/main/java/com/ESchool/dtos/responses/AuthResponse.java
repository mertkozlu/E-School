package com.ESchool.dtos.responses;

import lombok.Data;

@Data
public class AuthResponse {
    private String message;
    private Long studentId;
    private String accessToken;
}
