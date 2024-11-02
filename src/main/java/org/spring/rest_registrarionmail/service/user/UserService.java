package org.spring.rest_registrarionmail.service.user;

import org.spring.rest_registrarionmail.model.entity.User;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserByEmail(String email);
}
