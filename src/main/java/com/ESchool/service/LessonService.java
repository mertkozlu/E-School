package com.ESchool.service;

import com.ESchool.dataAccess.LessonRepository;
import com.ESchool.dtos.GetLessonByIdDto;
import com.ESchool.dtos.requests.AddLessonRequest;
import com.ESchool.dtos.requests.UpdateLessonRequest;
import com.ESchool.dtos.responses.GetAllLessonResponse;
import com.ESchool.entities.Lesson;
import com.ESchool.exception.BusinessException;
import com.ESchool.mappers.ModelMapperService;
import com.ESchool.result.*;
import com.ESchool.rules.LessonBusinessRules;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final ModelMapperService modelMapperService;
    private final LessonBusinessRules lessonBusinessRules;

    public LessonService(LessonRepository lessonRepository, ModelMapperService modelMapperService,
                         LessonBusinessRules lessonBusinessRules) {
        this.lessonRepository = lessonRepository;
        this.modelMapperService = modelMapperService;
        this.lessonBusinessRules = lessonBusinessRules;
    }


    public DataResult<List<GetAllLessonResponse>> getAllLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        List<GetAllLessonResponse> getAllLessonResponses = lessons.stream()
                .map(lesson -> this.modelMapperService.forResponse()
                        .map(lesson, GetAllLessonResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<List<GetAllLessonResponse>>
                (getAllLessonResponses, true, "lessons successfully listed.");

    }

    public Result addLesson(AddLessonRequest newLesson) {
        if (this.lessonBusinessRules.validateRequest(newLesson)) {
            Lesson lesson = this.modelMapperService.forRequest().map(newLesson, Lesson.class);
            this.lessonBusinessRules.existsByLessonName(lesson.getLessonName());
            lessonRepository.save(lesson);

            return new SuccessResult("Lesson successfully added.");
        } else
            return new ErrorResult("Lesson could not be added.");
    }


    public GetLessonByIdDto getLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new BusinessException("Lesson can not found."));
        GetLessonByIdDto getLessonByIdDto = convertLessonGetLessonByIdDto(lesson);

        return getLessonByIdDto;
    }

    public Lesson updateLesson(UpdateLessonRequest updateLessonRequest) {
        Lesson lesson = lessonRepository.findById(updateLessonRequest.getLessonId()).orElseThrow(
                () -> new BusinessException("Lesson can not found."));
        Lesson lessonToUpdate = modelMapperService.forRequest().map(updateLessonRequest, Lesson.class);

        return lessonRepository.save(lessonToUpdate);
    }

    public void deleteLesson(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new BusinessException("Lesson can not found."));
        this.lessonRepository.deleteById(lessonId);
    }

    public GetLessonByIdDto convertLessonGetLessonByIdDto(Lesson lesson) {
        GetLessonByIdDto getLessonByIdDto = new GetLessonByIdDto();
        getLessonByIdDto.setLessonId(lesson.getLessonId());
        getLessonByIdDto.setLessonName(lesson.getLessonName());

        return getLessonByIdDto;
    }

}
