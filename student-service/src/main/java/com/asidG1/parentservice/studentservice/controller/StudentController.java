package com.asidG1.parentservice.studentservice.controller;

import com.asidG1.parentservice.studentservice.model.DTOs.MarkDTO;
import com.asidG1.parentservice.studentservice.model.DTOs.StudentDTO;
import com.asidG1.parentservice.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        return ResponseEntity
                .ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable("id") Long studentId) {
        Optional<StudentDTO> student = this.studentService.getStudentById(studentId);

        if (student.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(student.get());

        }
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@Valid @RequestBody StudentDTO studentDTO,
                                                 UriComponentsBuilder uriComponentsBuilder) {

        long newStudentId = studentService.addStudent(studentDTO);

        studentDTO.setId(newStudentId);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/students/{id}")
                        .build(newStudentId))
                .body(studentDTO);
    }

    @PostMapping("/{id}/add/mark")
    public ResponseEntity<StudentDTO> addMarkToStudent(@PathVariable("id") Long studentId,
                                                       @Valid @RequestBody MarkDTO markDTO) {

        if (studentService.addMarkToStudent(studentId, markDTO)) {
            return ResponseEntity.
                    ok().
                    build();
        } else {
            return ResponseEntity.
                    notFound().
                    build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDTO> deleteStudentById(@PathVariable("id") Long studentId) {
        this.studentService.deleteStudentById(studentId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/{studentId}/club/{clubId}")
    public ResponseEntity<StudentDTO> addClubToStudent(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId) {
        StudentDTO optionalDesiredStudent = studentService.addClubToStudent(clubId, studentId);

            return ResponseEntity
                    .ok(optionalDesiredStudent);

    }

    @DeleteMapping("/{studentId}/club/{clubId}")
    public ResponseEntity<StudentDTO> removeClubFromStudent(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId) {
        StudentDTO optionalDesiredStudent = studentService.removeClubFromStudent(clubId, studentId);

            return ResponseEntity
                    .ok(optionalDesiredStudent);

    }

}
