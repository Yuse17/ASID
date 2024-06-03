package com.asidG1.parentservice.saga.feignclient;

import com.asidG1.parentservice.saga.model.DTOs.StudentDTO;
import com.asidG1.parentservice.saga.model.DTOs.StudentRegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

//http://localhost:8081/parents/{id}
@FeignClient(name = "STUDENT-SERVICE")
public interface StudentClient {

    @GetMapping("/students/{id}")
    ResponseEntity<StudentDTO> getStudentsById(@PathVariable("id") Long id);

    @PostMapping("/students")
    ResponseEntity<StudentRegisterDTO> addStudent(@Valid @RequestBody StudentRegisterDTO studentRegisterDTO);

    @PostMapping("/students/{studentId}/club/{clubId}")
    ResponseEntity<StudentRegisterDTO> addClubToStudent(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId);

    @DeleteMapping("/students/{studentId}/club/{clubId}")
    ResponseEntity<StudentRegisterDTO> removeClubFromStudent(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId);
}