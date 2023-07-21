package com.ESchool.dtos.responses;

import lombok.Data;

@Data
public class BaseResponse {
    private String resultCode;
    private String resultDescription;
}
