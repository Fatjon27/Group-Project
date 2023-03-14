package com.example.groupproject.controllers;

import com.example.groupproject.models.LoginUser;
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
    public String dashboard(Model model, HttpSession session) {
        Long loggedInUserId = (Long) session.getAttribute("loggedInUserId");
        if (loggedInUserId == null) {
            return "redirect:/";
        } else {
            User user = userService.findById(loggedInUserId);
            model.addAttribute("user", userService.findById(loggedInUserId));
            model.addAttribute("quotes",quoteService.findAll());
                return "dashboard";
            }
        }


    }

