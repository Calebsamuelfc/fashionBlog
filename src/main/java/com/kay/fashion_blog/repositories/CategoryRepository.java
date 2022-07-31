package com.kay.fashion_blog.repositories;

import com.kay.fashion_blog.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categories, Long> {

}
