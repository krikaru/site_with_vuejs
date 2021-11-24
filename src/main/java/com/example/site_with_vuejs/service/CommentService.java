package com.example.site_with_vuejs.service;

import com.example.site_with_vuejs.domain.Comment;
import com.example.site_with_vuejs.domain.Message;
import com.example.site_with_vuejs.domain.User;
import com.example.site_with_vuejs.domain.Views;
import com.example.site_with_vuejs.dto.EventType;
import com.example.site_with_vuejs.dto.ObjectType;
import com.example.site_with_vuejs.repo.CommentRepo;
import com.example.site_with_vuejs.util.WsSender;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAutor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);
        return commentFromDb;
    }
}
