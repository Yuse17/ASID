package com.asidG1.parentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.asidG1.parentservice.model.DTOs.ClubDTO;
//import com.asidG1.model.DTOs.StudentDTO;
import com.asidG1.parentservice.service.ClubService;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping
    public ResponseEntity<List<ClubDTO>> getAllClubs() {
        return ResponseEntity.
                ok(clubService.getAllClubs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubDTO> getClubById(@PathVariable("id") Long clubId) {
        Optional<ClubDTO> club = this.clubService.getClubById(clubId);

        if (club.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();

        } else {
            return ResponseEntity
                    .ok(club.get());

        }
    }

    @PostMapping
    public ResponseEntity<ClubDTO> addClub(@Valid @RequestBody ClubDTO clubDTO,
                                           UriComponentsBuilder uriComponentsBuilder) {

        long newClubId = clubService.addClub(clubDTO);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/clubs/{id}")
                        .build(newClubId))
                .build();
    }

    @PostMapping("/{clubId}/student/{studentId}")
    public ResponseEntity<ClubDTO> addStudentToClub(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId) {
        ClubDTO optionalDesiredStudent = clubService.addStudentToClub(clubId, studentId);

            return ResponseEntity
                    .ok(optionalDesiredStudent);

    }

    @DeleteMapping("/{clubId}/student/{studentId}")
    public ResponseEntity<ClubDTO> removeStudentFromClub(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId) {
        ClubDTO optionalDesiredStudent = clubService.removeStudentFromClub(clubId, studentId);

            return ResponseEntity
                    .ok(optionalDesiredStudent);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ClubDTO> changeClubName(@Valid @RequestBody ClubDTO clubDTO,
                                                  @PathVariable("id") Long id) {

        ClubDTO club = clubService.changeClubName(id, clubDTO);

        if (club == null) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.
                    ok(club);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClubDTO> deleteClubById(@PathVariable("id") Long clubId) {
        this.clubService.deleteClubById(clubId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
