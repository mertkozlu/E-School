package com.ESchool.service;

import com.ESchool.dataAccess.StudentLessonRepository;
import com.ESchool.dto.GetAllStudentLessonDto;
import com.ESchool.dto.requests.AddStudentLessonRequest;
import com.ESchool.dto.responses.GetAllStudentLessonResponse;
import com.ESchool.entities.StudentLesson;
import com.ESchool.exception.BusinessException;
import com.ESchool.mappers.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Service
public class StudentLessonService {
    private final StudentLessonRepository studentLessonRepository;
    private final ModelMapperService modelMapperService;

    public StudentLessonService(StudentLessonRepository studentLessonRepository, ModelMapperService modelMapperService) {
        this.studentLessonRepository = studentLessonRepository;
        this.modelMapperService = modelMapperService;
    }

    public GetAllStudentLessonResponse getAllStudentLessons() {
        GetAllStudentLessonResponse response = new GetAllStudentLessonResponse();
        List<GetAllStudentLessonDto> dtos = studentLessonRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertStudentLessonGetAllStudentLessonDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list");
        }
        response.setGetAllStudentLessonDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;
    }

    public StudentLesson addStudentLesson(AddStudentLessonRequest newStudentLesson) {
        StudentLesson studentLesson = modelMapperService.forRequest().map(newStudentLesson, StudentLesson.class);

        return studentLessonRepository.save(studentLesson);
    }

    public GetAllStudentLessonDto convertStudentLessonGetAllStudentLessonDto(StudentLesson studentLesson) {
        GetAllStudentLessonDto getAllStudentLessonDto = new GetAllStudentLessonDto();
        getAllStudentLessonDto.setStudentLessonId(studentLesson.getStudentLessonId());
        getAllStudentLessonDto.setPointOne(studentLesson.getPointOne());
        getAllStudentLessonDto.setPointTwo(studentLesson.getPointTwo());
        getAllStudentLessonDto.setGrade(studentLesson.getGrade());
        getAllStudentLessonDto.setDiscontinuity(studentLesson.getDiscontinuity());

        return getAllStudentLessonDto;
    }


}
