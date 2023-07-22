package com.ESchool.service;

import com.ESchool.dataAccess.LessonRepository;
import com.ESchool.dataAccess.StudentLessonRepository;
import com.ESchool.dataAccess.StudentRepository;
import com.ESchool.dtos.GetAllStudentDto;
import com.ESchool.dtos.GetStudentByIdDto;
import com.ESchool.dtos.requests.AddStudentRequest;
import com.ESchool.dtos.requests.UpdateStudentRequest;
import com.ESchool.dtos.responses.GetAllStudentResponse;
import com.ESchool.dtos.responses.GetStudentByIdResponse;
import com.ESchool.entities.Student;
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
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapperService modelMapperService;
    private final LessonRepository lessonRepository;
    private final StudentLessonRepository studentLessonRepository;

    public StudentService(StudentRepository studentRepository, ModelMapperService modelMapperService,
                          LessonRepository lessonRepository, StudentLessonRepository studentLessonRepository) {
        this.studentRepository = studentRepository;
        this.modelMapperService = modelMapperService;
        this.lessonRepository = lessonRepository;
        this.studentLessonRepository = studentLessonRepository;
    }

    public GetAllStudentResponse getAllStudents() {
        GetAllStudentResponse response = new GetAllStudentResponse();
        List<GetAllStudentDto> dtos = studentRepository.findAll()
                .stream()
                .filter(Objects::nonNull)
                .map(this::convertStudentGetAllStudentDto)
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dtos)) {
            throw new BusinessException("Empty list.");
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

    public GetStudentByIdResponse getStudentById(Long studentId) {
        GetStudentByIdResponse response = new GetStudentByIdResponse();
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new BusinessException("Student can not found."));

        List<GetStudentByIdDto> dtos = new ArrayList<>();
        dtos.add(convertStudentGetStudentByIdDto(student));

        response.setGetStudentByIdDto(dtos);
        response.setResultCode("1");
        response.setResultDescription("Success");

        return response;
    }

    public void updateStudent(UpdateStudentRequest updateStudentRequest) {
        Student student = studentRepository.findById(updateStudentRequest.getStudentId()).orElseThrow(
                () -> new BusinessException("Student can not found."));
        Student studentToUpdate = this.modelMapperService.forRequest().map(updateStudentRequest, Student.class);

        this.studentRepository.save(studentToUpdate);
    }

    public void deleteStudentById(Long studentId) {
        Integer studentCount = studentRepository.countStudent();
        if (studentCount <= 1) {
            throw new BusinessException("Student can not be deleted there most be add one student.");
        }
        StudentLesson studentLesson = studentLessonRepository.findByStudent_StudentId(studentId);
        if (Objects.nonNull(studentLesson)) {
            throw new BusinessException("Student can not be deleted while the student has studentLessons.");
        }
        this.studentRepository.deleteById(studentId);
    }

    public Student getStudentByStudentName(String studentName) {
        Student student = studentRepository.findByStudentName(studentName);
        if (student == null) {
            return student;
        } else {
            throw new BusinessException("Student already exists.");
        }
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public GetAllStudentDto convertStudentGetAllStudentDto(Student student) {
        GetAllStudentDto getAllStudentDto = new GetAllStudentDto();
        getAllStudentDto.setStudentId(student.getStudentId());
        getAllStudentDto.setStudentNumber(student.getStudentNumber());
        getAllStudentDto.setStudentName(student.getStudentName());

        return getAllStudentDto;
    }

    public GetStudentByIdDto convertStudentGetStudentByIdDto(Student student) {
        GetStudentByIdDto getStudentByIdDto = new GetStudentByIdDto();
        getStudentByIdDto.setStudentId(student.getStudentId());
        getStudentByIdDto.setStudentNumber(student.getStudentNumber());
        getStudentByIdDto.setStudentName(student.getStudentName());

        return getStudentByIdDto;
    }


}
