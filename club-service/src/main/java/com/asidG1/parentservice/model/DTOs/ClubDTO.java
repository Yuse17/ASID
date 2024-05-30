package com.asidG1.parentservice.model.DTOs;

import java.util.Set;

import com.asidG1.parentservice.model.validation.UniqueClubName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClubDTO {

    @NotBlank
    @Size(min = 2, max = 20)
    @UniqueClubName
    private String name;

    @NotBlank
    @Size(min = 5, max = 500)
    private String description;

    @NotNull
    private Long town;

    private Set<Long> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getStudents(){
        return students; 
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTown() {
        return town;
    }

    public void setTown(Long town) {
        this.town = town;
    }

    public void setStudents(Set<Long> students){
        this.students = students;
    }
}
