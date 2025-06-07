package com.blog.controller;

import com.blog.entity.User;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model , Authentication auth) {
        if (auth == null) {
            return;
        }
        String username = auth.getName();
        User user = userService.getUserByEmail(username);
        model.addAttribute("loggedInUser", user);
    }
}
