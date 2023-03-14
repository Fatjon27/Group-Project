package com.example.groupproject.services;

import com.example.groupproject.models.LoginUser;
import com.example.groupproject.models.User;
import com.example.groupproject.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public ArrayList<User> findAll(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User updateUser(User user){
        return userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User register(User newUser, BindingResult result){
        Optional<User> potentialUser = this.userRepository.findByEmail(newUser.getEmail()) ;
        if(potentialUser.isPresent()) {
            result.rejectValue("email","Exists","This email already exists");
        }
        if(!newUser.getPassword().equals(newUser.getConfirm())){
            result.rejectValue("confirm","Matches","Password and Confirm Password must be the same");
        }
        if(result.hasErrors()){
            return null;
        }
        else {
            String hashed = BCrypt.hashpw(newUser.getPassword(),BCrypt.gensalt());
            newUser.setPassword(hashed);
            return userRepository.save(newUser);
        }
    }

    //Bussines logic to login a existing user
    public User login(LoginUser newLoginObject, BindingResult result){
        Optional<User> potentialUser = this.userRepository.findByEmail(newLoginObject.getEmail());
        if(!potentialUser.isPresent()){
            result.rejectValue("email","Invalid","This email does not exist");
        }
        else {
            if(!BCrypt.checkpw(newLoginObject.getPassword(),potentialUser.get().getPassword())){
                result.rejectValue("password","Incorrect","The password is not correct");
            }
        }
        if(result.hasErrors()){
            return null;
        }
        else {
            return potentialUser.get();
        }
    }
}
