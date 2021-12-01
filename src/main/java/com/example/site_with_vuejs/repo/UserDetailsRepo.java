package com.example.site_with_vuejs.repo;

import com.example.site_with_vuejs.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepo extends JpaRepository<User, String> {
    @EntityGraph(attributePaths = { "subscriptions", "subscribers" }) //eager
    Optional<User> findById(String s);
}
