package com.ESchool.dtos.requests;

import lombok.Data;

@Data
public class RefreshRequest {

    private Long studentId;
    private String refreshToken;

}
