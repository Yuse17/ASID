package com.asidG1.parentservice.saga.feignclient;

import com.asidG1.parentservice.saga.model.DTOs.ParentDTO;
import com.asidG1.parentservice.saga.model.DTOs.ParentRegisterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

//http://localhost:8081/parents/{id}
@FeignClient(name = "PARENT-SERVICE")
public interface ParentClient {

    @GetMapping("/parents/{id}")
    ResponseEntity<ParentDTO> getParentById(@PathVariable("id") Long id);

    @PostMapping("/parents")
    ResponseEntity<ParentRegisterDTO> addParent(@Valid @RequestBody ParentRegisterDTO parentDTO);

    @DeleteMapping("/parents/{id}")
    ResponseEntity<ParentRegisterDTO> deleteParentById(@PathVariable("id") Long parentId);
}
