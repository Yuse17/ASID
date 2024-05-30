package com.asidG1.parentservice.saga.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.asidG1.parentservice.saga.feignclient.ParentClient;
import com.asidG1.parentservice.saga.feignclient.StudentClient;
import com.asidG1.parentservice.saga.feignclient.TownClient;
import com.asidG1.parentservice.saga.model.DTOs.ParentDTO;
import com.asidG1.parentservice.saga.model.DTOs.ParentRegisterDTO;
import com.asidG1.parentservice.saga.model.DTOs.StudentDTO;
import com.asidG1.parentservice.saga.model.DTOs.StudentRegisterDTO;
import com.asidG1.parentservice.saga.model.DTOs.TownDTO;

import feign.FeignException;

@Service
public class StudentService {

    private final ModelMapper mapper;

    @Autowired
    private ParentClient parentClient;

    @Autowired
    private StudentClient studentClient;

    @Autowired
    private TownClient townClient;

    @Autowired
    public StudentService(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public ResponseEntity<TownDTO> registarCidade(TownDTO townDTO){
        try {
            ResponseEntity<TownDTO> existingTownResponse = townClient.getTownByName(townDTO.getName());
            HttpHeaders headers = new HttpHeaders();
            headers.addAll(existingTownResponse.getHeaders());
            headers.add("cidadeCriada", "false");
            return ResponseEntity.status(existingTownResponse.getStatusCode())
                             .headers(headers)
                             .body(existingTownResponse.getBody());
        } catch (FeignException e) {
            try{
                ResponseEntity<TownDTO> createdTownResponse = townClient.addTown(townDTO);
                HttpHeaders headers = new HttpHeaders();
                headers.addAll(createdTownResponse.getHeaders());
                headers.add("cidadeCriada", "true");
                return ResponseEntity.status(createdTownResponse.getStatusCode())
                                    .headers(headers)
                                    .body(createdTownResponse.getBody());
            }catch(FeignException ee){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    }

    public ResponseEntity<ParentRegisterDTO> registarPai(ParentDTO parentDTO) {
        // Separar cidade
        TownDTO townParent = parentDTO.getTown();

        //Criar a cidade
         ResponseEntity<TownDTO> responseTown = registarCidade(townParent);

         if(responseTown.getStatusCode().is5xxServerError() || responseTown.getStatusCode().is4xxClientError()){
             System.out.println("Cancelado apos erro criar cidade pai");
             System.out.println(responseTown.getStatusCode());
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
         }

         Boolean cidadeCriada = Boolean.parseBoolean(responseTown.getHeaders().get("cidadeCriada").get(0));
         System.out.println("Cidade Pai criada: " + cidadeCriada);

        ParentRegisterDTO parentRegister = new ParentRegisterDTO();
        parentRegister.setAge(parentDTO.getAge());
        parentRegister.setEGN(parentDTO.getEGN());
        parentRegister.setEmail(parentDTO.getEmail());
        parentRegister.setFirstName(parentDTO.getFirstName());
        parentRegister.setGender(parentDTO.getGender());
        parentRegister.setId(parentDTO.getId());
        parentRegister.setLastName(parentDTO.getLastName());
        parentRegister.setMiddleName(parentDTO.getMiddleName());
        parentRegister.setPhoneNumber(parentDTO.getPhoneNumber());
        parentRegister.setTown(responseTown.getBody().getId());

        // Usar API ParentService para registar o pai, se der erro, apaga a cidade se foi criada

        ResponseEntity<ParentRegisterDTO> response = null;
        try{
            response = parentClient.addParent(parentRegister);
            HttpHeaders headers = new HttpHeaders();
             headers.addAll(response.getHeaders());
            headers.add("cidadeCriada", cidadeCriada.toString());
            return ResponseEntity.status(response.getStatusCode())
                                .headers(headers)
                                .body(response.getBody());
        }catch (FeignException e){
            if(cidadeCriada == true){
                ResponseEntity<TownDTO> responseTownDel = townClient.deleteTown(responseTown.getBody().getId());
                System.out.println("Cidade apagada apos erro criar pai");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Registar estudante com o pai
    public ResponseEntity<StudentRegisterDTO> registarEstudante(StudentDTO studentDTO) {

        // Se existir Parent, separar entre StudentDTO e ParentDTO
        ParentDTO parentDTO = studentDTO.getParent();

        //Criar o pai, se der erro, apagar a cidade
        ResponseEntity<ParentRegisterDTO> responsePai = registarPai(parentDTO);

        if(responsePai.getStatusCode().is5xxServerError() || responsePai.getStatusCode().is4xxClientError()){
            System.out.println("Cancelado apos erro criar pai");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        ParentRegisterDTO parentToMap = responsePai.getBody();
        System.out.println("Pai criado:" + parentToMap);
        Boolean cidadePaiCriada = Boolean.parseBoolean(responsePai.getHeaders().get("cidadeCriada").get(0));
        System.out.println("Cidade pai criada: " + cidadePaiCriada);


        TownDTO studentTown = studentDTO.getTown(); 
        ResponseEntity<TownDTO> responseTown = registarCidade(studentTown);

        if(responseTown.getStatusCode().is5xxServerError() || responseTown.getStatusCode().is4xxClientError()){
            System.out.println("Cancelado ao criar cidade estudante");
            System.out.println(responseTown.getStatusCode());
            ResponseEntity<ParentRegisterDTO> responseParentDel = parentClient.deleteParentById(parentToMap.getId());
            System.out.println("Pai eliminado apos erro cidade estudante");
            if(cidadePaiCriada){
                ResponseEntity<TownDTO> responseTownDel = townClient.deleteTown(parentToMap.getTown());
                System.out.println("Cidade pai eliminada apos erro cidade estudante");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        Boolean cidadeEstudanteCriada = Boolean.parseBoolean(responseTown.getHeaders().get("cidadeCriada").get(0));
        System.out.println("Cidade estudante criada: " + cidadeEstudanteCriada);

        // Se não, usar API StudentService para registar aluno
        StudentRegisterDTO student = new StudentRegisterDTO();
        student.setAge(studentDTO.getAge());
        student.setEGN(studentDTO.getEGN());
        student.setEmail(studentDTO.getEmail());
        student.setFirstName(studentDTO.getEmail());
        student.setGender(studentDTO.getGender());
        student.setId(studentDTO.getId());
        student.setLastName(studentDTO.getLastName());
        student.setMiddleName(studentDTO.getMiddleName());
        student.setParent(parentToMap.getId());
        student.setTown(responseTown.getBody().getId());

        ResponseEntity<StudentRegisterDTO> responseStudent = null;
        try{
            responseStudent = studentClient.addStudent(student);
            return responseStudent;
        }catch (FeignException e){
            if(cidadeEstudanteCriada == true){
                ResponseEntity<TownDTO> responseTownDel = townClient.deleteTown(responseTown.getBody().getId());
                System.out.println("Cidade estudante apagada apos erro criar estudante");
            }
            ResponseEntity<ParentRegisterDTO> responseParentDel = parentClient.deleteParentById(parentToMap.getId());
            System.out.println("Pai eliminado apos erro criar estudante");
            if(cidadePaiCriada){
                ResponseEntity<TownDTO> responseTownDel = townClient.deleteTown(parentToMap.getTown());
                System.out.println("Cidade pai eliminada apos erro criar estudante");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }
}
