package com.example.site_with_vuejs.repo;

import com.example.site_with_vuejs.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
