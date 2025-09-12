package com.sinaukoding.eventbookingsystem.repository.management_user;

import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String lowerCase, String id);
}
