package com.blog.service;

import com.blog.entity.Blog;
import com.blog.entity.User;

import java.util.List;

public interface BlogService {
    Blog save(Blog blog);

    Blog getById(String id);

    List<Blog> getAll();

    void delete(String id);

    Blog update(Blog blog);

    Blog getUserWithId(String id);

    List<Blog> getByUserId(String userId);

    List<Blog> getByUser(User user);
}
