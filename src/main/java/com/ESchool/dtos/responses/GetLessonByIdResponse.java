package com.ESchool.dtos.responses;

import com.ESchool.dtos.GetLessonByIdDto;
import lombok.Data;

import java.util.List;

@Data
public class GetLessonByIdResponse extends BaseResponse {
    List<GetLessonByIdDto> getLessonByIdDto;

}
