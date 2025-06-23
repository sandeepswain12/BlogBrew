package com.blog.controller;

import com.blog.dtos.UserDto;
import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.helper.Message;
import com.blog.helper.MessageType;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class PageController {
    Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/blogs";
    }

    @GetMapping("/blogs")
    public String blogs(Model model) {
        List<Blog> blogs = blogService.getAll();
        model.addAttribute("blogs", blogs);
        return "blogs";
    }
    @GetMapping("/signup")
    public String signup(Model  model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "signup";
    }
    @PostMapping("/signup")
    public String signupProcessing(@Valid @ModelAttribute UserDto userDto,BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.info(error.toString()));
            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "signup";
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setEnabled(true);
        User savedUser = userService.save(user);
        Message message = Message.builder().content("Sign in Successful!!").type(MessageType.green).build();
        session.setAttribute("message", message);
        return "redirect:/signup";
    }
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/service")
    public String service() {
        return "service";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/blogview/{id}")
    public String blogview(@PathVariable String id, Model model) {
        Blog blog = blogService.getUserWithId(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a");
        model.addAttribute("blog", blog);
        model.addAttribute("dateAndTimeFormated", blog.getCreatedDate().format(formatter));
        return "blogview";
    }
}
