package com.ESchool.dtos.responses;

import com.ESchool.dtos.GetStudentByIdDto;
import lombok.Data;

import java.util.List;

@Data
public class GetStudentByIdResponse extends BaseResponse {
    List<GetStudentByIdDto> getStudentByIdDto;
}
