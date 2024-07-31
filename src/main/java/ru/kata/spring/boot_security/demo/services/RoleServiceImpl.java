package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.enteties.Role;
import ru.kata.spring.boot_security.demo.reposotories.RoleRepository;

import java.util.Collection;


@Service
public class RoleServiceImpl implements RoleService {

    final private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Collection<Role> listRoles() {
        return roleRepository.findAll();
    }

}
