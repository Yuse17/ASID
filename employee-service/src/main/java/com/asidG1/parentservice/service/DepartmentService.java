package com.asidG1.parentservice.service;

import com.asidG1.parentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asidG1.parentservice.model.entity.Department;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    public Department findDepartmentById(Long departmentID) {
        return departmentRepository.findById(departmentID).orElseThrow();
    }
}
