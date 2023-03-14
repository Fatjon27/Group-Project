package com.example.groupproject.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUser {
    @NotBlank(message = "Email is required to login")
    @Email
    @Size(min=5,message = "Email must be longer")
    private String email;
    @NotBlank(message = "Password is required to login")
    @Size(min=8,max=128,message = "Password must be longer")
    private String password;
    public LoginUser(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
