package com.example.groupproject.controllers;

import com.example.groupproject.services.CommentService;
import com.example.groupproject.services.QuoteService;
import com.example.groupproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ProjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private CommentService commentService;
}
