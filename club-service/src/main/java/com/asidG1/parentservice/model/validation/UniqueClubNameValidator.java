package com.asidG1.parentservice.model.validation;

import com.asidG1.parentservice.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueClubNameValidator implements ConstraintValidator<UniqueClubName, String> {

    private final ClubRepository clubRepository;

    @Autowired
    public UniqueClubNameValidator(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public boolean isValid(String clubName, ConstraintValidatorContext context) {
        return clubRepository.
                findByName(clubName).
                isEmpty();
    }
}
