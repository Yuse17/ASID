package com.asidG1.parentservice.model.DTOs;

import jakarta.validation.constraints.NotBlank;

public class DepartmentDTO {

    private Long id;

    @NotBlank
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
