package com.ESchool.service;

import com.ESchool.dataAccess.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsServiceImpl {
    private final StudentRepository studentRepository;

    public StudentDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
