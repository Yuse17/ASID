package com.asidG1.parentservice.saga.model.DTOs;

import java.time.LocalDate;
import java.util.Set;

import com.asidG1.parentservice.saga.model.DTOs.base.PersonEntityDTO;

public class StudentDTO extends PersonEntityDTO{
    private final LocalDate enrollDate = LocalDate.now();

    private ParentDTO parent;

    private Set<Long> clubs;

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public ParentDTO getParent() {
        return parent;
    }

    public Set<Long> getClubs(){
        return clubs;
    }

    public void setClubs(Set<Long> clubs){
        this.clubs = clubs; 
    }

    public void setParent(ParentDTO parent) {
        this.parent = parent;
    }
}
