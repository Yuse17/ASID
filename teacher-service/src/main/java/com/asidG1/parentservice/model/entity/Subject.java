package com.asidG1.parentservice.model.entity;

import com.asidG1.parentservice.model.entity.base.BaseEntityWithIdLong;
import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject extends BaseEntityWithIdLong {

    @Column(unique = true, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
