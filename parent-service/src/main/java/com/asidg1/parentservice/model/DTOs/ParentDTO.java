package com.asidg1.parentservice.model.DTOs;

import jakarta.validation.constraints.NotBlank;

import com.asidg1.parentservice.model.DTOs.base.PersonEntityDTO;

public class ParentDTO extends PersonEntityDTO {

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
