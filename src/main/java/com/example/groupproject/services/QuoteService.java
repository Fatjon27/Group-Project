package com.example.groupproject.services;

import com.example.groupproject.models.Comment;
import com.example.groupproject.models.Quote;
import com.example.groupproject.repositories.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;
    @Autowired
    private CommentService commentService;

    public ArrayList<Quote> findAll(){
        return quoteRepository.findAll();
    }

    public Quote createQuote(Quote quote, BindingResult result) {
        if (quote.getAuthor().isEmpty()){
            result.rejectValue("author","null","Author should not be null");
        }
        if (quote.getDescription().isEmpty()){
            result.rejectValue("description","null","Description should not be empty");
        }
        if (result.hasErrors()){
            return null;
        }

        return quoteRepository.save(quote);
    }

    public Quote updateQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Quote findById(Long id){
        return quoteRepository.findById(id).orElse(null);
    }

    public void deleteQuote(Long id) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);
        //List<Comment> com = quote.getComments();
        if (optionalQuote.isPresent()) {
            for ( Comment com : optionalQuote.get().getComments()){
                com.setQuote(null);
                commentService.updateComment(com);
            }
            quoteRepository.deleteById(id);

        } else return;
    }

}
