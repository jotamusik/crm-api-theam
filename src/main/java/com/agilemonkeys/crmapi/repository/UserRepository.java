package com.agilemonkeys.crmapi.repository;

import com.agilemonkeys.crmapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(int id);
}
