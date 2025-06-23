package com.blog.controller;

import com.blog.dtos.BlogDto;
import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.helper.Helper;
import com.blog.helper.Message;
import com.blog.helper.MessageType;
import com.blog.service.BlogService;
import com.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/user")
public class BlogController {

    Logger logger = LoggerFactory.getLogger(BlogController.class);

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @GetMapping("/blog/add")
    public String createBlog(Model model) {
        BlogDto blogDto = new BlogDto();
        model.addAttribute("blogDto",blogDto);
        return "user/createblog";
    }

    @PostMapping("/blog/add")
    public String addBlog(@Valid @ModelAttribute BlogDto blogDto, BindingResult bindingResult, Authentication authentication, HttpSession session) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.info(error.toString()));
            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/createblog";
        }
        User user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
        Blog blog = new Blog();
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blog.setUser(user);
        blogService.save(blog);
        session.setAttribute("message",
                Message.builder()
                        .content("You have successfully added a new Blog")
                        .type(MessageType.green)
                        .build());
        return "redirect:/user/blog/add";
    }

    @GetMapping("/blog/edit/{id}")
    public String editBlog(@PathVariable String id, Model model) {
        Blog blog = blogService.getById(id);
        BlogDto blogDto = new BlogDto();
        blogDto.setTitle(blog.getTitle());
        blogDto.setContent(blog.getContent());
        model.addAttribute("blogDto",blogDto);
        model.addAttribute("blogId",id);
        return "user/edit";
    }

    @PostMapping("/blog/edit/{id}")
    public String editProcess(@PathVariable String id,@Valid @ModelAttribute BlogDto blogDto, BindingResult bindingResult,HttpSession session) {
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.info(error.toString()));
            session.setAttribute("message", Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build());
            return "user/edit";
        }
        Blog blog = blogService.getById(id);
        blog.setTitle(blogDto.getTitle());
        blog.setContent(blogDto.getContent());
        blogService.update(blog);
        session.setAttribute("message",
                Message.builder()
                        .content("Blog updated Successfully")
                        .type(MessageType.green)
                        .build());
        return "redirect:/user/blog/edit/" + id;
    }

    @GetMapping("/blog/delete/{id}")
    public String deleteBlog(@PathVariable String id) {
        blogService.delete(id);
        return "redirect:/user/profile";
    }

}
