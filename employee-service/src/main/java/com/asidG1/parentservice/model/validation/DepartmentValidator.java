package com.asidG1.parentservice.model.validation;

import com.asidG1.parentservice.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.asidG1.parentservice.model.DTOs.DepartmentDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DepartmentValidator implements ConstraintValidator<ValidDepartment, DepartmentDTO> {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public boolean isValid(DepartmentDTO department, ConstraintValidatorContext context) {
        return departmentRepository.findById(department.getId()).isPresent();
    }
}
