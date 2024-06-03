package com.asidG1.parentservice.studentservice.model.DTOs;

import com.asidG1.parentservice.studentservice.model.DTOs.base.PersonEntityDTO;

import java.time.LocalDate;
import java.util.Set;

public class StudentDTO extends PersonEntityDTO {

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

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public void setClubs(Set<Long> clubs){
        this.clubs = clubs; 
    }
}
