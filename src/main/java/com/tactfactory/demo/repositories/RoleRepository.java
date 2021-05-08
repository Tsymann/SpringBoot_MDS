package com.tactfactory.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tactfactory.demo.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
