package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.enteties.User;

import java.util.List;

public interface UserService {
    User getUserByUserName(String userName);

    void saveUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    void updateUser(User updateUser);

    List<User> getAllUsers();
}

