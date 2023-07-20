package com.ESchool.dto.responses;

import com.ESchool.dto.GetAllLessonDto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllLessonResponse extends BaseResponse {
    List<GetAllLessonDto> getAllLessonDto;
}
