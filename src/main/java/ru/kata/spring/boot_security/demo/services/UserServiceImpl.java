package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.kata.spring.boot_security.demo.enteties.Role;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.reposotories.RoleRepository;
import ru.kata.spring.boot_security.demo.reposotories.UserRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format(" User '%s' not found", username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNameRole())).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User getUserByUserName(String userName) {

        return userRepository.findByUsername(userName);
    }
    @Override
    @Transactional
    public void saveUser(User user) {
        if (user.getId()!=null && userRepository.findById(user.getId()).isPresent()) {
            throw new UsernameNotFoundException(String.format("Пользователь с ID '%s' уже существует. Сохранение невозможно", user.getUsername()));
        }
        user.setRoles(Collections.singleton(new Role(1l, "ROLE_USER")));
        user.setRoles(Collections.singleton(new Role(2l, "ROLE_ADMIN")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }
    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(new User());

    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(User updateUser) {
        User user = userRepository.findById(updateUser.getId()).orElse(new User());
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setEmail(updateUser.getEmail());
        user.setRoles(updateUser.getRoles());
        userRepository.save(user);

    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}