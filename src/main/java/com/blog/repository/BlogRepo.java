package com.blog.repository;

import com.blog.entity.Blog;
import com.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogRepo extends JpaRepository<Blog, String> {
    List<Blog> findByUser(User user);
    @Query("SELECT b FROM Blog b WHERE b.user.userId = :userId")
    List<Blog> findByUserId (@Param("userId") String userId);
    @Query("SELECT b FROM Blog b JOIN FETCH b.user WHERE b.id = :id")
    Blog findByIdWithUser(@Param("id") String id);

}
