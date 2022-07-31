package com.kay.fashion_blog.controller;


import com.kay.fashion_blog.dto.PostResponsePayload;
import com.kay.fashion_blog.dto.SearchDto;
import com.kay.fashion_blog.entity.Posts;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;
import com.kay.fashion_blog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/view_post/{post_id}")
    public ResponseEntity<PostResponsePayload> getPost(@PathVariable("post_id") Long postId) throws ResourceNotFoundException {
        var response = postService.retrievePost(postId);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/view_all_post")
    public ResponseEntity<List<PostResponsePayload>> viewAllPosts(){
        var response = postService.viewAllPosts();
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/search_post")
    public ResponseEntity<List<PostResponsePayload>> SearchPostByTittle(@RequestBody SearchDto searchDto)  {
        var response = postService.searchPostByTitle(searchDto);
        return ResponseEntity.ok().body(response);
    }
}
