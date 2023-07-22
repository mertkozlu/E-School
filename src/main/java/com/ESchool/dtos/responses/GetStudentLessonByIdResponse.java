package com.ESchool.dtos.responses;

import com.ESchool.dtos.GetStudentLessonByIdDto;
import lombok.Data;

import java.util.List;

@Data
public class GetStudentLessonByIdResponse extends BaseResponse {
    List<GetStudentLessonByIdDto> getStudentLessonByIdDto;
}
