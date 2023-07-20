package com.ESchool.service;

import com.ESchool.dataAccess.StudentRepository;
import com.ESchool.dto.GetAllStudentDto;
import com.ESchool.dto.responses.GetAllStudentResponse;
import com.ESchool.entities.Student;
import com.ESchool.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public GetAllStudentResponse getAllStudents() {
        GetAllStudentResponse response = new GetAllStudentResponse();
        List<GetAllStudentDto> dtos = studentRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertStudentGetAllStudentDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list");
        }
        response.setGetAllStudentDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;
    }

    public GetAllStudentDto convertStudentGetAllStudentDto(Student student) {
        GetAllStudentDto getAllStudentDto = new GetAllStudentDto();
        getAllStudentDto.setStudentId(student.getStudentId());
        getAllStudentDto.setStudentName(student.getStudentName());
        getAllStudentDto.setPassword(student.getPassword());

        return getAllStudentDto;
    }
}
