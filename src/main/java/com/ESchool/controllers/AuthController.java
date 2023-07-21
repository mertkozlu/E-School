package com.ESchool.controllers;

import com.ESchool.dataAccess.StudentRepository;
import com.ESchool.dtos.requests.LoginRequest;
import com.ESchool.dtos.requests.StudentRequest;
import com.ESchool.dtos.responses.AuthResponse;
import com.ESchool.entities.Student;
import com.ESchool.security.JwtTokenProvider;
import com.ESchool.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                          PasswordEncoder passwordEncoder, StudentService studentService,
                          StudentRepository studentRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.studentService = studentService;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(loginRequest.getStudentName(), loginRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        Student student = studentService.getStudentByStudentName(loginRequest.getStudentName());
        AuthResponse authResponse = new AuthResponse();
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setMessage("User login success");
        authResponse.setStudentId(student.getStudentId());
        return authResponse;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody StudentRequest registerRequest) {
        AuthResponse authResponse = new AuthResponse();
        if (studentService.getStudentByStudentName(registerRequest.getStudentName()) != null) {
            authResponse.setMessage("Username already in use");
            return new ResponseEntity<>(authResponse, HttpStatus.BAD_REQUEST);
        }
        Student student = new Student();
        student.setStudentName(registerRequest.getStudentName());
        student.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        student.setStudentNumber(registerRequest.getStudentNumber());
        studentService.save(student);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(registerRequest.getStudentName(), registerRequest.getPassword());
        Authentication auth = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwtToken = jwtTokenProvider.generateJwtToken(auth);

        authResponse.setMessage("User successfully registered");
        authResponse.setAccessToken("Bearer " + jwtToken);
        authResponse.setStudentId(student.getStudentId());
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

}
