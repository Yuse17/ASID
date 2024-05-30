package com.asidG1.parentservice.model.entity;

import com.asidG1.parentservice.model.entity.base.BaseEntityWithIdLong;
import jakarta.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends BaseEntityWithIdLong {

    @Column
    private String name;

    @Column
    private Long habitantes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private Country country;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Long getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(Long habitantes) {
        this.habitantes = habitantes;
    }

}
