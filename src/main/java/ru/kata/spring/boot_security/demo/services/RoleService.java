package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.enteties.Role;

import java.util.Collection;

public interface RoleService {
    Collection<Role> listRoles();

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);


}
