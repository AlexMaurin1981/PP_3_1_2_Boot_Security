package ru.kata.spring.boot_security.demo.services;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.enteties.Role;
import ru.kata.spring.boot_security.demo.reposotories.RoleRepository;

import java.util.Collection;


@Service
public class RoleServiceImpl implements RoleService {

private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override

    public Collection<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Override

    public void addRole(Role role) {
    roleRepository.save(role);
    }

    @Override

    public void updateRole(Role role) {
    roleRepository.save(role);
    }

    @Override

    public void deleteRole(Role role) {
    roleRepository.delete(role);
    }
}
