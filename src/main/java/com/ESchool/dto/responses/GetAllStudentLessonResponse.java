package com.ESchool.dto.responses;

import com.ESchool.dto.GetAllStudentLessonDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllStudentLessonResponse extends BaseResponse {
    List<GetAllStudentLessonDto> getAllStudentLessonDto;
}
