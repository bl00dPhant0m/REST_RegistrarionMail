package org.spring.rest_registrarionmail.service.userRegistration;

import lombok.RequiredArgsConstructor;
import org.spring.rest_registrarionmail.exeption.UserConfirmationException;
import org.spring.rest_registrarionmail.mapper.UserMapper;
import org.spring.rest_registrarionmail.model.DTO.UserRegistrationDTO;
import org.spring.rest_registrarionmail.model.entity.PendingUser;
import org.spring.rest_registrarionmail.model.entity.User;
import org.spring.rest_registrarionmail.service.mail.MailService;
import org.spring.rest_registrarionmail.service.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserService userService;
    private final UserMapper userMapper;
    private Map<String, PendingUser> pendingUsers = new ConcurrentHashMap<>();
    private final MailService mailService;

    @Override
    public void registrationUser(UserRegistrationDTO user) {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Пароли не совпадают");
        }

        if (userService.getUserByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        }

        String code = generateCode();
        PendingUser pendingUser = new PendingUser(user, code, LocalDateTime.now());
        pendingUsers.put(user.getEmail(), pendingUser);

        String message = "Ваш код для подтверждения регистрации: " + code;

        mailService.sendEmail(user.getEmail(), "Подтверждение пароля", message);
    }

    @Override
    public void confirm(String email, String code) {
        PendingUser pendingUser = pendingUsers.get(email);

        if (pendingUser == null) {
            throw new UserConfirmationException("Код не действительный.");
        }

        if (!pendingUser.getCode().equals(code)) {
            throw new UserConfirmationException("Неверный код подтверждения.");
        }

        if (pendingUser.isCodeExpired()) {
            throw new UserConfirmationException("Код подтверждения истек. Пожалуйста, повторите регистрацию.");
        }

        User user = userMapper.toUser(pendingUser.getRegistration());
        user.setActive(true);
        userService.saveUser(user);
        pendingUsers.remove(email);
    }

    public String generateCode(){
        Random random = new Random();
        return String.valueOf(random.nextInt(1_000_000));
    }
}
