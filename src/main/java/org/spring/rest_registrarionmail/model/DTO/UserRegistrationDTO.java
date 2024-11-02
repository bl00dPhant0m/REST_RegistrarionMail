package org.spring.rest_registrarionmail.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDTO {
    @Size(min = 6, max = 32, message = "Логин должен быть от 6 до 32 символов")
    @NotBlank(message = "Логин не должен быть пустым")
    private String username;

    @Size(min = 6, max = 32, message = "Пароль должен быть от 6 до 32 символов")
    @NotBlank(message = "Пароль не должен быть пустым")
    @Pattern(regexp = ".*[a-zA-Zа-яА-Я].*", message = "Пароль должен содержать хотя бы одну букву")
    private String password;

    @NotBlank(message = "Не должен быть пустым")
    private String confirmPassword;

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Не верный формат email")
    private String email;
}
