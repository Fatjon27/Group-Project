package com.example.groupproject.services;

import com.example.groupproject.models.Comment;
import com.example.groupproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public ArrayList<Comment> findAll(){
        return commentRepository.findAll();
    }

    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findById(Long id){
        return commentRepository.findById(id).orElse(null);
    }

}
