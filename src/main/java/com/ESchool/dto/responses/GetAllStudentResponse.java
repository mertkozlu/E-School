package com.ESchool.dto.responses;

import com.ESchool.dto.GetAllStudentDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllStudentResponse extends BaseResponse {
    List<GetAllStudentDto> getAllStudentDto;
}
