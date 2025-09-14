package com.sinaukoding.eventbookingsystem.repository.management_user;

import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String lowerCase, String id);
    Boolean existsByUsername(String username);
    Boolean existsByUsernameAndIdNot(String username, String id);
    Optional<User> findByUsername(String username);
}
