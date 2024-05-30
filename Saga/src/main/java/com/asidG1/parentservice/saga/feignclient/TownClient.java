package com.asidG1.parentservice.saga.feignclient;

import com.asidG1.parentservice.saga.model.DTOs.TownDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

//http://localhost:8081/parents/{id}
@FeignClient(name = "TOWN-SERVICE")
public interface TownClient {

    @GetMapping("/towns/id/{id}")
    ResponseEntity<TownDTO> getTownById(@PathVariable("id") Long id);

    @GetMapping("/towns/name/{name}")
    ResponseEntity<TownDTO> getTownByName(@PathVariable("name") String name);

    @PostMapping("/towns")
    ResponseEntity<TownDTO> addTown(@Valid @RequestBody TownDTO townDTO);

    @DeleteMapping("/towns/{id}")
    ResponseEntity<TownDTO> deleteTown(@PathVariable("id") Long townId);
}