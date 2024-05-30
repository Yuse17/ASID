package com.asidG1.parentservice.model.entity;

import jakarta.persistence.*;

import com.asidG1.parentservice.model.entity.base.BaseEntityWithIdLong;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "clubs")
public class Club extends BaseEntityWithIdLong {

    @Column(unique = true)
    private String name;

    @Column(length = 500, columnDefinition = "text")
    private String description;

    @Column
    private Long town;

    @ElementCollection
    @CollectionTable(name = "club_students", joinColumns = @JoinColumn(name = "club_id"))
    @Column(name = "student_id")
    private Set<Long> students = new HashSet<>();

    public Club() {
        this.students = new HashSet<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addStudentToClub(Long student) {
        students.add(student);
    }

    public void removeStudentFromClub(Long student) {
        students.remove(student);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<Long> getStudents() {
        return Collections.unmodifiableSet(students);
    }

    public Long getTown(){
        return town;
    }

    public void setTown(Long town){
        this.town = town; 
    }

    @Override
    public String toString() {
        return "Club's name: " + name + System.lineSeparator()
                + "Club's description: " + description;
    }
}
