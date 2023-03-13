package com.example.groupproject.repositories;

import com.example.groupproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public ArrayList<User> findAll();

    Optional<User> findByEmail(String email);
}
