package com.kay.fashion_blog.repositories;

import com.kay.fashion_blog.entity.Comments;
import com.kay.fashion_blog.entity.Posts;
import com.kay.fashion_blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long> {
//    List<Comments> findAllByPosts(Posts posts);
}
