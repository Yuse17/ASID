package com.asidG1.parentservice.model.validation;

import jakarta.validation.*;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ValidTownValidator.class)
public @interface ValidTown {

    String message() default "Invalid Town";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
