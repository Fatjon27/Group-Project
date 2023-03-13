package com.example.groupproject.services;

import com.example.groupproject.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;
}
