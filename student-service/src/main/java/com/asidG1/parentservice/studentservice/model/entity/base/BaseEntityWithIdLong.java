package com.asidG1.parentservice.studentservice.model.entity.base;

import jakarta.persistence.*;


@MappedSuperclass
public abstract class BaseEntityWithIdLong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
