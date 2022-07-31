package com.kay.fashion_blog.repositories;

import com.kay.fashion_blog.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    List<Posts> findPostsByTitleContainsIgnoreCase(String title);
}
