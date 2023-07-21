package com.ESchool.dtos.responses;

import com.ESchool.dtos.GetAllStudentDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllStudentResponse extends BaseResponse {
    List<GetAllStudentDto> getAllStudentDto;
}
