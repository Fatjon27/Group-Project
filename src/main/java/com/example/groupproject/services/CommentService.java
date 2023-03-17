package com.example.groupproject.services;

import com.example.groupproject.models.Comment;
import com.example.groupproject.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public ArrayList<Comment> findAll(){
        return commentRepository.findAll();
    }

    public Comment createComment(Comment comment, BindingResult result) {
        if (comment.getText().isEmpty()){
            result.rejectValue("Text","null","Text should not be empty");
        }
        if (result.hasErrors()){
            return null;
        }

        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findById(Long id){
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);


        } else return;
    }

}
