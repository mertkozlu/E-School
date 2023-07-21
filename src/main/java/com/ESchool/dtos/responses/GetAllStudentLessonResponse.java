package com.ESchool.dtos.responses;

import com.ESchool.dtos.GetAllStudentLessonDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllStudentLessonResponse extends BaseResponse {
    List<GetAllStudentLessonDto> getAllStudentLessonDto;
}
