package com.asidG1.parentservice.model.DTOs;

import jakarta.validation.constraints.NotBlank;

public class TownDTO {

    private Long id;

    @NotBlank
    private String name;

    private CountryDTO country;

    private Long habitantes; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }
    
    public Long getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Long habitantes) {
        this.habitantes = habitantes;
    }
}
