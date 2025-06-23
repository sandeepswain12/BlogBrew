package com.blog.controller;

import com.blog.entity.User;
import com.blog.helper.Helper;
import com.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addAttributes(Model model , Authentication auth) {
        if (auth == null) {
            return;
        }
        logger.info("adding user attributes");
        String username = Helper.getEmailOfLoggedInUser(auth);
        logger.info("logged in username: " + username);
        User user = userService.getUserByEmail(username);
        logger.info("logged in user: " + user.toString());
        model.addAttribute("loggedInUser", user);
    }
}
