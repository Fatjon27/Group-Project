package com.example.groupproject.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name must be provided.")
    @Size(min=3,max=30,message = "Name must be at least 3 characters.")
    private String name;
    @Email
    @NotBlank(message = "Email must be provided.")
    @Size(min=5,message = "Email must be at least 5 characters.")
    private String email;
    @NotBlank(message = "Password must be provided.")
    @Size(min=8,max=128,message = "Password must be at least 8 characters.")
    private String password;
    @Transient
    @NotBlank(message = "Confirm must be provided.")
    @Size(min=8,max=128,message = "Confirm must be at least 8 characters.")
    private String confirm;
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Quote> quotes;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Comment> comments;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "quote_id"))
    private List<Quote> likes;
    public User() {
    }

    public User(Long id, String name, String email, String password, String confirm, Date createdAt, Date updatedAt, List<Quote> quotes, List<Comment> comments, List<Quote> likes) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.quotes = quotes;
        this.comments = comments;
        this.likes = likes;
    }

    public User(Long id, String name, String email, String password, String confirm, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.confirm = confirm;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }

    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Quote> getLikes() {
        return likes;
    }

    public void setLikes(List<Quote> likes) {
        this.likes = likes;
    }
}
