package com.asidG1.parentservice.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.asidG1.parentservice.model.DTOs.TownDTO;
import com.asidG1.parentservice.service.TownService;

@RestController
@RequestMapping("/towns")
public class TownController {
    
    private final TownService townService; 

    @Autowired
    public TownController(TownService townService){
        this.townService = townService; 
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<TownDTO> getTownByID(@PathVariable("id") Long townId) {
        TownDTO town = this.townService.findByTownId(townId);

            return ResponseEntity
                    .ok(town);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<TownDTO> getTownByName(@PathVariable("name") String townName) {
        TownDTO town = this.townService.findByTownName(townName);

            return ResponseEntity
                    .ok(town);

    }

    @PostMapping
    public ResponseEntity<TownDTO> addTown(@Valid @RequestBody TownDTO townDTO,
                                                 UriComponentsBuilder uriComponentsBuilder) {

        long newTownId = townService.addTown(townDTO);

        townDTO.setId(newTownId);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/towns/{id}")
                        .build(newTownId))
                .body(townDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TownDTO> deleteParentById(@PathVariable("id") Long townId) {
        this.townService.deleteTownById(townId);

        return ResponseEntity
                .noContent()
                .build();
    }
}
