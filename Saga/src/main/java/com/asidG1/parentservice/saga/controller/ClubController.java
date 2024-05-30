package com.asidG1.parentservice.saga.controller;

import com.asidG1.parentservice.saga.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clubs")
public class ClubController {
    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping("/{clubId}/student/{studentId}")
    public ResponseEntity<?> addStudenttoClub(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId) {

        ResponseEntity<?> newClubStudent = clubService.addStudentToClub(studentId, clubId);

        return newClubStudent;

    }
}
