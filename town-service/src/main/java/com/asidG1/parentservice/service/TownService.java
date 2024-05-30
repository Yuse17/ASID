package com.asidG1.parentservice.service;

import com.asidG1.parentservice.model.DTOs.TownDTO;
import com.asidG1.parentservice.model.entity.Town;
import com.asidG1.parentservice.repository.TownRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TownService {

    private final TownRepository townRepository;

    private final ModelMapper mapper;

    @Autowired
    public TownService(TownRepository townRepository, ModelMapper mapper) {
        this.townRepository = townRepository;
        this.mapper = mapper;
    }

    private TownDTO mapToTownDTO(Town town) {
        return mapper.map(town, TownDTO.class);
    }

    public TownDTO findByTownId(Long townId){
        return this.townRepository.findById(townId).map(this::mapToTownDTO).orElseThrow();
    }


    public TownDTO findByTownName(String townName){
        return this.townRepository.findByName(townName).map(this::mapToTownDTO).orElseThrow();
    }

    public long addTown(TownDTO townDTO) {

        //Condiçao se nao existir o pais criar

        Town town = mapper.map(townDTO, Town.class);
        townRepository.save(town);

        return town.getId();

    }

    public void deleteTownById(Long townId) {
        this.townRepository.deleteById(townId);
    }


}
