package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.enteties.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserByUserName(String userName);

    @Transactional
    void saveUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    void updateUser(User updateUser);

    List<User> getAllUsers();
}

