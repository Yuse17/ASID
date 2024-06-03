package com.asidG1.parentservice.saga.feignclient;

import com.asidG1.parentservice.saga.model.DTOs.ClubDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

//http://localhost:8081/parents/{id}
@FeignClient(name = "CLUB-SERVICE")
public interface ClubClient {

    @GetMapping("/clubs/{id}")
    ResponseEntity<ClubDTO> getClubByID(@PathVariable("id") Long id);

    /*@PostMapping("/clubs")
    ResponseEntity<ClubRegisterDTO> addClub(@Valid @RequestBody ClubRegisterDTO clubDTO);*/

    @PostMapping("/clubs/{clubId}/student/{studentId}")
    ResponseEntity<ClubDTO> addStudentToClub(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId);

    @DeleteMapping("/clubs/{clubId}/student/{studentId}")
    ResponseEntity<ClubDTO> removeStudentToClub(@PathVariable("clubId") Long clubId, @PathVariable("studentId") Long studentId);
}
