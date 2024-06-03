package com.asidG1.parentservice.model.entity;

import com.asidG1.parentservice.model.entity.base.BaseEntityWithIdLong;
import jakarta.persistence.*;


import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country extends BaseEntityWithIdLong {

    @Column
    private String name;

    @OneToMany(mappedBy = "country")
    private Set<Town> towns;

    public Country(){
        this.towns = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Town> getTowns() {
        return Collections.unmodifiableSet(towns);
    }

}
