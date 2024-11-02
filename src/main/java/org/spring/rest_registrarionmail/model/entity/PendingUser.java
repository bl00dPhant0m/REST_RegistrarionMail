package org.spring.rest_registrarionmail.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.spring.rest_registrarionmail.model.DTO.UserRegistrationDTO;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PendingUser {
    private UserRegistrationDTO registration;
    private String code;
    private LocalDateTime createdAt;


    public boolean isCodeExpired(){
        return createdAt.plusMinutes(1).isBefore(LocalDateTime.now());
    }
}
