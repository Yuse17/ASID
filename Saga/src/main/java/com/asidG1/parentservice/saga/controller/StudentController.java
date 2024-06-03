package com.asidG1.parentservice.saga.controller;

import com.asidG1.parentservice.saga.model.DTOs.StudentDTO;
import com.asidG1.parentservice.saga.model.DTOs.StudentRegisterDTO;
import com.asidG1.parentservice.saga.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /*@GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        return ResponseEntity
                .ok(studentService.getAllStudents());
    }*/

    @PostMapping
    public ResponseEntity<StudentRegisterDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO,
                                                         UriComponentsBuilder uriComponentsBuilder) {

        ResponseEntity<StudentRegisterDTO> newStudentId = studentService.registarEstudante(studentDTO);

        return newStudentId;
    }
}
