package com.example.site_with_vuejs.repo;

import com.example.site_with_vuejs.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {

}
