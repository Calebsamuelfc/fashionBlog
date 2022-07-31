package com.kay.fashion_blog.service;

import com.kay.fashion_blog.dto.CategoryDto;
import com.kay.fashion_blog.dto.CommentDto;
import com.kay.fashion_blog.entity.Comments;
import com.kay.fashion_blog.entity.Users;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;

import java.util.List;


public interface UserService {
    Users findUserById(Long id);
    // deleteComment
    String deleteComment(Long id) throws NotAuthorizedException;
    Comments getComment(Long id) throws ResourceNotFoundException;
    String editComment(CommentDto commentDto, Long commentId) throws NotAuthorizedException, ResourceNotFoundException;
    String createComment(CommentDto commentDto, Long postId) throws ResourceNotFoundException;
    String likePost(Long postId) throws ResourceNotFoundException;
    List<Comments> viewAllComments (Long postId) throws ResourceNotFoundException;
    String createCategory(CategoryDto categoryDto);
    Integer getNumberOfLikes(Long postId) throws ResourceNotFoundException;
}
