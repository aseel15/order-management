package com.example.wholeProject.repositries;

import com.example.wholeProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

        User findByUsername(String username);
}
