package com.asidG1.parentservice.saga.service;

import com.asidG1.parentservice.saga.model.DTOs.ClubDTO;
import com.asidG1.parentservice.saga.model.DTOs.StudentRegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.asidG1.parentservice.saga.feignclient.ClubClient;
import com.asidG1.parentservice.saga.feignclient.StudentClient;

import feign.FeignException;

@Service
public class ClubService {
    private final ModelMapper mapper;

    @Autowired
    private StudentClient studentClient; 

    @Autowired
    private ClubClient clubClient;

    @Autowired
    public ClubService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ResponseEntity<?> addStudentToClub(Long studentID, Long clubID) {
        boolean addedToClub = false;
        try{
            ResponseEntity<ClubDTO> responseClub = clubClient.addStudentToClub(clubID, studentID);
            addedToClub = true;
            System.out.println("Estudante adicionado ao clube");
            ResponseEntity<StudentRegisterDTO> responseStudent = studentClient.addClubToStudent(clubID, studentID);
            System.out.println("Club adicionado ao estudante");
            return ResponseEntity.ok(responseClub.getBody());
        }catch(FeignException e){
            if(addedToClub){
                ResponseEntity<ClubDTO> responseClub = clubClient.removeStudentToClub(clubID, studentID);
                System.out.println("Estudante removido do club apos erro adicionar club ao estudante");
            }
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nao foi possivel adicionar o estudante ao club");
        }

    
    }
}
