package org.spring.rest_registrarionmail.service.user;

import lombok.RequiredArgsConstructor;
import org.spring.rest_registrarionmail.model.entity.User;
import org.spring.rest_registrarionmail.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
