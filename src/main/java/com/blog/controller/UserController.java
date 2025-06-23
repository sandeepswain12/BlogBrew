package com.blog.controller;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.helper.Helper;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;


    @RequestMapping("/profile")
    public String Profile(Model model, Authentication authentication) {
        User user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
        List<Blog> blogs = blogService.getByUser(user);
        model.addAttribute("blogs",blogs);
        return "user/profile";
    }
}
