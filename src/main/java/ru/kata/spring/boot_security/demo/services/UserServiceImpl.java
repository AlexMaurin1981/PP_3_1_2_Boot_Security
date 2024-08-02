package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.enteties.Role;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.reposotories.UserRepository;


import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;



    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(new User());
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User updateUser) {
        User user = userRepository.findById(updateUser.getId()).orElseThrow(() -> new IllegalArgumentException("User not found"));
        String currentPassword = user.getPassword();
        String newPassword = updateUser.getPassword();
        if (!currentPassword.equals(newPassword)) {
            updateUser.setPassword((new BCryptPasswordEncoder().encode(updateUser.getPassword())));
        }
        userRepository.save(updateUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
