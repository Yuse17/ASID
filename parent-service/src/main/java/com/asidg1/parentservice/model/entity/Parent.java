package com.asidg1.parentservice.model.entity;

import jakarta.persistence.*;

import com.asidg1.parentservice.model.entity.base.BasePersonEntity;

@Entity
@Table(name = "parents")
public class Parent extends BasePersonEntity {

    @Column
    private String phoneNumber;

    /*@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    Set<Student> students;

    public Parent(){
        this.students = new HashSet<>();
    }*/

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /*public Set<Student> getStudents() {
        return Collections.unmodifiableSet(students);
    }*/
}
