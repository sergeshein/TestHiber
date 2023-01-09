package com.example.testhiber.repository;

import com.example.testhiber.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findFirstByName(String name);
}
