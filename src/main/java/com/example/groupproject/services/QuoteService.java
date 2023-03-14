package com.example.groupproject.services;

import com.example.groupproject.models.Quote;
import com.example.groupproject.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    public ArrayList<Quote> findAll(){
        return quoteRepository.findAll();
    }

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote updateQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote findById(Long id){
        return quoteRepository.findById(id).orElse(null);
    }

}
