package com.ESchool.service;

import com.ESchool.dataAccess.LessonRepository;
import com.ESchool.dataAccess.StudentLessonRepository;
import com.ESchool.dataAccess.StudentRepository;
import com.ESchool.dtos.GetAllStudentLessonDto;
import com.ESchool.dtos.GetStudentLessonByIdDto;
import com.ESchool.dtos.requests.AddStudentLessonRequest;
import com.ESchool.dtos.requests.UpdateStudentLessonRequest;
import com.ESchool.dtos.responses.GetAllStudentLessonResponse;
import com.ESchool.dtos.responses.GetStudentLessonByIdResponse;
import com.ESchool.entities.StudentLesson;
import com.ESchool.exception.BusinessException;
import com.ESchool.mappers.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentLessonService {
    private final StudentLessonRepository studentLessonRepository;
    private final ModelMapperService modelMapperService;
    private final StudentRepository studentRepository;
    private final LessonRepository lessonRepository;

    public StudentLessonService(StudentLessonRepository studentLessonRepository, ModelMapperService modelMapperService,
                                StudentRepository studentRepository, LessonRepository lessonRepository) {
        this.studentLessonRepository = studentLessonRepository;
        this.modelMapperService = modelMapperService;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
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

    public GetStudentLessonByIdResponse getStudentLessonById(Long studentLessonId) {
        GetStudentLessonByIdResponse response = new GetStudentLessonByIdResponse();
        StudentLesson studentLesson = studentLessonRepository.findById(studentLessonId).orElseThrow(
                () -> new BusinessException("StudentLesson can not found."));

        List<GetStudentLessonByIdDto> dtos = new ArrayList<>();
        dtos.add(convertStudentLessonGetStudentLessonById(studentLesson));

        response.setGetStudentLessonByIdDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;

    }

    public StudentLesson updateStudentLesson(UpdateStudentLessonRequest updateStudentLessonRequest) {
        StudentLesson studentLesson = studentLessonRepository.findById(updateStudentLessonRequest.getStudentLessonId())
                .orElseThrow(() -> new BusinessException("StudentLesson can not found."));
        StudentLesson studentLessonToUpdate = this.modelMapperService.forRequest().map(updateStudentLessonRequest, StudentLesson.class);

        return studentLessonRepository.save(studentLessonToUpdate);
    }

    public void deleteStudentLesson(Long studentLessonId) {
        StudentLesson studentLesson = studentLessonRepository.findById(studentLessonId).orElseThrow(
                () -> new BusinessException("StudentLesson can not found."));
        this.studentLessonRepository.deleteById(studentLessonId);
    }

    public GetAllStudentLessonDto convertStudentLessonGetAllStudentLessonDto(StudentLesson studentLesson) {
        GetAllStudentLessonDto getAllStudentLessonDto = new GetAllStudentLessonDto();
        getAllStudentLessonDto.setStudentLessonId(studentLesson.getStudentLessonId());
        getAllStudentLessonDto.setStudentId(studentLesson.getStudent().getStudentId());
        getAllStudentLessonDto.setStudentName(studentLesson.getStudent().getStudentName());
        getAllStudentLessonDto.setLessonId(studentLesson.getLesson().getLessonId());
        getAllStudentLessonDto.setLessonName(studentLesson.getLesson().getLessonName());
        getAllStudentLessonDto.setPointOne(studentLesson.getPointOne());
        getAllStudentLessonDto.setPointTwo(studentLesson.getPointTwo());
        getAllStudentLessonDto.setGrade(studentLesson.getGrade());
        getAllStudentLessonDto.setDiscontinuity(studentLesson.getDiscontinuity());

        return getAllStudentLessonDto;
    }

    public GetStudentLessonByIdDto convertStudentLessonGetStudentLessonById(StudentLesson studentLesson) {
        GetStudentLessonByIdDto getStudentLessonByIdDto = new GetStudentLessonByIdDto();
        getStudentLessonByIdDto.setStudentLessonId(studentLesson.getStudentLessonId());
        getStudentLessonByIdDto.setStudentId(studentLesson.getStudent().getStudentId());
        getStudentLessonByIdDto.setStudentName(studentLesson.getStudent().getStudentName());
        getStudentLessonByIdDto.setLessonId(studentLesson.getLesson().getLessonId());
        getStudentLessonByIdDto.setLessonName(studentLesson.getLesson().getLessonName());
        getStudentLessonByIdDto.setPointOne(studentLesson.getPointOne());
        getStudentLessonByIdDto.setPointTwo(studentLesson.getPointTwo());
        getStudentLessonByIdDto.setGrade(studentLesson.getGrade());
        getStudentLessonByIdDto.setDiscontinuity(studentLesson.getDiscontinuity());

        return getStudentLessonByIdDto;
    }

}
