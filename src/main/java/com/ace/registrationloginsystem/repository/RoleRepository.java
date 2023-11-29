package com.ace.registrationloginsystem.repository;

import com.ace.registrationloginsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
