package com.ESchool.service;

import com.ESchool.dataAccess.StudentRepository;
import com.ESchool.entities.Student;
import com.ESchool.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentDetailsServiceImpl implements UserDetailsService {
    private final StudentRepository studentRepository;

    public StudentDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String studentName) throws UsernameNotFoundException {
        Student student = studentRepository.findByStudentName(studentName);
        return JwtUserDetails.create(student);
    }


    public UserDetails loadUserById(Long studentId) {
        Student student = studentRepository.findById(((studentId))).orElseThrow(() ->
                new UsernameNotFoundException("User not found with id : " + studentId));
        return JwtUserDetails.create(student);
    }
}
