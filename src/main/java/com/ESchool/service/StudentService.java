package com.ESchool.service;

import com.ESchool.dataAccess.StudentRepository;
import com.ESchool.dtos.GetAllStudentDto;
import com.ESchool.dtos.requests.AddStudentRequest;
import com.ESchool.dtos.responses.GetAllStudentResponse;
import com.ESchool.entities.Student;
import com.ESchool.exception.BusinessException;
import com.ESchool.mappers.ModelMapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapperService modelMapperService;

    public StudentService(StudentRepository studentRepository, ModelMapperService modelMapperService) {
        this.studentRepository = studentRepository;
        this.modelMapperService = modelMapperService;
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

    public Student addStudent(AddStudentRequest newStudent) {
//        Student student = new Student();
//        student.setStudentNumber(newStudent.getStudentNumber());
//        student.setStudentName(newStudent.getStudentName());

        Student student = modelMapperService.forRequest().map(newStudent, Student.class);

        return studentRepository.save(student);
    }

    public GetAllStudentDto convertStudentGetAllStudentDto(Student student) {
        GetAllStudentDto getAllStudentDto = new GetAllStudentDto();
        getAllStudentDto.setStudentId(student.getStudentId());
        getAllStudentDto.setStudentNumber(student.getStudentNumber());
        getAllStudentDto.setStudentName(student.getStudentName());

        return getAllStudentDto;
    }



}
