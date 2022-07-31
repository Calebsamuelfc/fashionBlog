package com.kay.fashion_blog.repositories;

import com.kay.fashion_blog.entity.Likes;
import com.kay.fashion_blog.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
}
