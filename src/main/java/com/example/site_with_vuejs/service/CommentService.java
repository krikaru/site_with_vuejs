package com.example.site_with_vuejs.service;

import com.example.site_with_vuejs.domain.Comment;
import com.example.site_with_vuejs.domain.User;
import com.example.site_with_vuejs.repo.CommentRepo;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment create(Comment comment, User user) {
        comment.setAutor(user);
        commentRepo.save(comment);

        return comment;
    }
}
