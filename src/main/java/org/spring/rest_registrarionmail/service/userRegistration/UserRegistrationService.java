package org.spring.rest_registrarionmail.service.userRegistration;

import org.spring.rest_registrarionmail.model.DTO.UserRegistrationDTO;

public interface UserRegistrationService {
    void registrationUser(UserRegistrationDTO user);
    void confirm(String email, String code);
}
