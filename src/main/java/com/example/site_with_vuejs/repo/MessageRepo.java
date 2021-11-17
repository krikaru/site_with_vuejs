package com.example.site_with_vuejs.repo;

import com.example.site_with_vuejs.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
