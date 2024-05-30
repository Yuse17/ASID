package com.asidG1.parentservice.saga.model.validation;

import jakarta.validation.*;


public class ValidEGNValidator implements ConstraintValidator<ValidEGN, String> {


    @Override
    public boolean isValid(String EGN, ConstraintValidatorContext context) {
        return EGN.length() == 10;
    }
}
