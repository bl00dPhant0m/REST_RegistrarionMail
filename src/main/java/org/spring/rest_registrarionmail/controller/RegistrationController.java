package org.spring.rest_registrarionmail.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.spring.rest_registrarionmail.model.DTO.UserRegistrationDTO;
import org.spring.rest_registrarionmail.service.userRegistration.UserRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserRegistrationService userRegistrationService;

    @PostMapping("/registration")
    public ResponseEntity<Void> getRegistrationUser(@Valid @RequestBody UserRegistrationDTO user) {
        userRegistrationService.registrationUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/expired")
    public ResponseEntity<Void> getExpiredUser(@RequestParam(name = "email") String email, @RequestParam(name = "code") String code) {
        userRegistrationService.confirm(email, code);
        return ResponseEntity.ok().build();
    }

}
