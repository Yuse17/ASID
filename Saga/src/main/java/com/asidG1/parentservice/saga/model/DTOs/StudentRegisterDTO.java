package com.asidG1.parentservice.saga.model.DTOs;

import java.time.LocalDate;
import java.util.Set;

import com.asidG1.parentservice.saga.model.DTOs.base.PersonEntityRegisterDTO;

public class StudentRegisterDTO extends PersonEntityRegisterDTO{
    private final LocalDate enrollDate = LocalDate.now();

    private Long parent;

    private Set<Long> clubs;

    public LocalDate getEnrollDate() {
        return enrollDate;
    }

    public Long getParent() {
        return parent;
    }
    public Set<Long> getClubs(){
        return clubs;
    }

    public void setClubs(Set<Long> clubs){
        this.clubs = clubs; 
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
}
