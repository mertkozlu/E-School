package com.ESchool.service;

import com.ESchool.dataAccess.LessonRepository;
import com.ESchool.dtos.GetAllLessonDto;
import com.ESchool.dtos.requests.AddLessonRequest;
import com.ESchool.dtos.responses.GetAllLessonResponse;
import com.ESchool.entities.Lesson;
import com.ESchool.exception.BusinessException;
import com.ESchool.mappers.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapperService modelMapperService;

    public LessonService(LessonRepository lessonRepository, ModelMapperService modelMapperService) {
        this.lessonRepository = lessonRepository;
        this.modelMapperService = modelMapperService;
    }


    public GetAllLessonResponse getAllLessons() {
        GetAllLessonResponse response = new GetAllLessonResponse();
        List<GetAllLessonDto> dtos = lessonRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertLessonGetAllLessonDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list");
        }
        response.setGetAllLessonDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;
    }

    public Lesson addLesson(AddLessonRequest newLesson) {
        Lesson lesson = this.modelMapperService.forRequest().map(newLesson, Lesson.class);

        return lessonRepository.save(lesson);
    }

    public GetAllLessonDto convertLessonGetAllLessonDto(Lesson lesson) {
        GetAllLessonDto getAllLessonDto = new GetAllLessonDto();
        getAllLessonDto.setLessonId(lesson.getLessonId());
        getAllLessonDto.setLessonName(lesson.getLessonName());

        return getAllLessonDto;
    }

}
