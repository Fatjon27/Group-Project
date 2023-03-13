package com.example.groupproject.repositories;

import com.example.groupproject.models.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {

    public ArrayList<Quote> findAll();


}
