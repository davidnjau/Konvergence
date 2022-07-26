package com.dave.konvergenz.backend.authentication.repository;


import com.dave.konvergenz.backend.authentication.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long > {

    Role findByName(String name);
    Boolean existsByName(String roleName);

}
