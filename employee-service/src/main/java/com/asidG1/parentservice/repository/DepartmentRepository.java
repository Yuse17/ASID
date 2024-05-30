package com.asidG1.parentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asidG1.parentservice.model.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {


}
