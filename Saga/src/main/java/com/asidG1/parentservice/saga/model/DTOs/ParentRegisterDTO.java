package com.asidG1.parentservice.saga.model.DTOs;

import com.asidG1.parentservice.saga.model.DTOs.base.PersonEntityRegisterDTO;

import jakarta.validation.constraints.NotBlank;

public class ParentRegisterDTO extends PersonEntityRegisterDTO {

    @NotBlank
    //@PhoneNumber
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
