package com.ESchool.dtos.responses;

import com.ESchool.dtos.GetAllLessonDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllLessonResponse extends BaseResponse {
    List<GetAllLessonDto> getAllLessonDto;
}
