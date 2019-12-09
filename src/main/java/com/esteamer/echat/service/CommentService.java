package com.esteamer.echat.service;

import com.esteamer.echat.domain.Comment;
import com.esteamer.echat.domain.Message;
import com.esteamer.echat.domain.User;
import com.esteamer.echat.domain.Views;
import com.esteamer.echat.dto.EventType;
import com.esteamer.echat.dto.ObjectType;
import com.esteamer.echat.repo.CommentRepo;
import com.esteamer.echat.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepo commentRepo;

    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE,commentFromDb);


        return comment;
    }

}
