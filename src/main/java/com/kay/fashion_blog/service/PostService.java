package com.kay.fashion_blog.service;

import com.kay.fashion_blog.dto.PostRequestPayload;
import com.kay.fashion_blog.dto.PostResponsePayload;
import com.kay.fashion_blog.dto.SearchDto;
import com.kay.fashion_blog.entity.Posts;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;

import java.util.List;

public interface PostService {
    String createPost(PostRequestPayload postRequestPayload, Long catId) throws NotAuthorizedException, ResourceNotFoundException;
    PostResponsePayload retrievePost(Long postId) throws ResourceNotFoundException;
    String editPost(PostRequestPayload postRequestPayload, Long postId) throws ResourceNotFoundException, NotAuthorizedException;
    String deletePost(Long id) throws NotAuthorizedException;
    List<PostResponsePayload> viewAllPosts ();
    List<PostResponsePayload> searchPostByTitle(SearchDto searchDto);
}
