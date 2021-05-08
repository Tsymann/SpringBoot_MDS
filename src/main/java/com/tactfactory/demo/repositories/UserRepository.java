package com.tactfactory.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tactfactory.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
