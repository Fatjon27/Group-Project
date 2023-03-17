package com.example.groupproject.controllers;

import com.example.groupproject.models.Comment;
import com.example.groupproject.models.LoginUser;
import com.example.groupproject.models.Quote;
import com.example.groupproject.models.User;
import com.example.groupproject.services.CommentService;
import com.example.groupproject.services.QuoteService;
import com.example.groupproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ProjectController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private CommentService commentService;


    @GetMapping("/")
    public String index(Model model, @ModelAttribute("newUser") User newUser, @ModelAttribute("newLogin") LoginUser newLogin) {
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "register";
    }

    //Post Mapping for registering the user
    @PostMapping("/register")
    public String register(Model model, HttpSession session, @Valid @ModelAttribute("newUser") User newUser, BindingResult result) {
        userService.register(newUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "register";
        } else {
            session.setAttribute("loggedInUserId", newUser.getId());
            return "redirect:/dashboard";
        }
    }

    //Post Mapping for loging-in the user
    @PostMapping("/login")
    public String login(Model model, HttpSession session, @Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result) {
        User user = this.userService.login(newLogin, result);
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "register";
        } else {
            session.setAttribute("loggedInUserId", user.getId());
            return "redirect:/dashboard";
        }
    }

    //GetMapping to logout the current user using session
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    //Main Page that shows up when a user registers,login
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session, @ModelAttribute("quote") Quote quote, @ModelAttribute("comment") Comment comment) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            return "redirect:/";
        } else {
            User user = userService.findById(loggedInUserId);
            model.addAttribute("user", userService.findById(loggedInUserId));
            model.addAttribute("quotes", quoteService.findAll());
            model.addAttribute("comments", commentService.findAll());

            return "dashboard";
        }
    }

    @PostMapping("/new/quotes")
    public String createQuote(@ModelAttribute("quote") Quote quote, BindingResult result, Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            return "redirect:/";
        } else {
            User user = userService.findById(loggedInUserId);
            model.addAttribute("user", userService.findById(loggedInUserId));
            if (result.hasErrors()) {
                model.addAttribute("quote", new Quote());
                return "dashboard";
            } else {
                quote.setUser(user);
                quoteService.createQuote(quote,result);
                return "redirect:/dashboard";
            }
        }
    }

    @PostMapping("/new/comment/{id}")
    public String createComment(@ModelAttribute("comment") Comment comment, BindingResult result, Model model, HttpSession session, @PathVariable("id") Long quoteId) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        Quote quote = quoteService.findById(quoteId);
        if (loggedInUserId == null) {
            return "redirect:/";
        } else {
            User user = userService.findById(loggedInUserId);
            model.addAttribute("user", userService.findById(loggedInUserId));
            if (result.hasErrors()) {
                model.addAttribute("quote", new Quote());
                return "dashboard";
            } else {
                Comment comment1 = new Comment(comment.getText(), comment.getCreatedAt(), comment.getUpdatedAt());
                comment1.setUser(user);
                comment1.setQuote(quote);
                commentService.createComment(comment1,result);
                return "redirect:/dashboard";
            }
        }
    }

    @PostMapping("/new/like/{id}")
    public String addLike(@PathVariable("id") Long quoteId, Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        Quote quote = quoteService.findById(quoteId);
        if (loggedInUserId == null) {
            return "redirect:/";
        } else {
            User user = userService.findById(loggedInUserId);
            model.addAttribute("user", userService.findById(loggedInUserId));
            user.getLikes().add(quote);
            userService.updateUser(user);
            return "redirect:/dashboard";

        }
    }
    @PostMapping("/remove/like/{id}")
    public String removeLike(@PathVariable("id") Long quoteId, Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        Quote quote = quoteService.findById(quoteId);
        if (loggedInUserId == null) {
            return "redirect:/";
        } else {
            User user = userService.findById(loggedInUserId);
            model.addAttribute("user", userService.findById(loggedInUserId));
            user.getLikes().remove(quote);
            userService.updateUser(user);
            return "redirect:/dashboard";

        }
    }

    @GetMapping( "/quote/{id}/delete" )
    public String deleteQuote( @PathVariable( "id" ) Long id) {

        quoteService.deleteQuote( id);

        return "redirect:/dashboard";
    }
    @GetMapping( "/comment/{id}/delete" )
    public String deleteComment( @PathVariable( "id" ) Long id ) {

        commentService.deleteComment( id );


        return "redirect:/dashboard";
    }


    }

