package com.blog.service.impl;

import com.blog.entity.Blog;
import com.blog.entity.User;
import com.blog.repository.BlogRepo;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepo blogRepo;

    @Override
    public Blog save(Blog blog) {
        String blogId = UUID.randomUUID().toString();
        blog.setId(blogId);
        return blogRepo.save(blog);
    }

    @Override
    public Blog getById(String id) {
        return blogRepo.findById(id).orElseThrow(() -> new RuntimeException("blog not found"));
    }

    @Override
    public List<Blog> getAll() {
        return blogRepo.findAll();
    }

    @Override
    public void delete(String id) {
        Blog blog = blogRepo.findById(id).orElseThrow();
        blogRepo.delete(blog);
    }

    @Override
    public Blog update(Blog blog) {
        var oldBlog = blogRepo.findById(blog.getId()).orElse(null);
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setContent(blog.getContent());
        return blogRepo.save(oldBlog);
    }

    @Override
    public Blog getUserWithId(String id) {
        return blogRepo.findByIdWithUser(id);
    }

    @Override
    public List<Blog> getByUserId(String userId) {
        return List.of();
    }



    @Override
    public List<Blog> getByUser(User user) {
        return blogRepo.findByUser(user);
    }
}
