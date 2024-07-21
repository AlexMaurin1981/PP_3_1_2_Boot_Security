package ru.kata.spring.boot_security.demo.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.enteties.Role;
    @Repository
    public interface RoleRepository extends JpaRepository<Role, Long> {


    }
