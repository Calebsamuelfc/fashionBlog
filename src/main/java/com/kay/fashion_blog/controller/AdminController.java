package com.kay.fashion_blog.controller;

import com.kay.fashion_blog.dto.CategoryDto;
import com.kay.fashion_blog.dto.PostRequestPayload;
import com.kay.fashion_blog.dto.SignUpDto;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;
import com.kay.fashion_blog.service.AuthenticationService;
import com.kay.fashion_blog.service.PostService;
import com.kay.fashion_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final PostService postService;


    @DeleteMapping("/delete_comment/{comment_id}")
    public ResponseEntity<String> deleteComment(@PathVariable("comment_id") Long commentId) throws NotAuthorizedException {
        var response = userService.deleteComment(commentId);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/create_category")
    public ResponseEntity<String> createCategory(@RequestBody CategoryDto categoryDto) {
        var response = userService.createCategory(categoryDto);
        return ResponseEntity.ok().body(response);
    }
    @PostMapping("/auth/signup")
    public ResponseEntity<String> register(@RequestBody SignUpDto signUpDto){
        var response = authenticationService.signUpAdmin(signUpDto);
        return ResponseEntity.ok().body(response);
    }
    @DeleteMapping("/delete_post/{post_id}")
    public ResponseEntity<String> deletePost(@PathVariable("post_id") Long postId) throws NotAuthorizedException {
        var response = postService.deletePost(postId);
        return ResponseEntity.ok().body(response);
    }
    @SneakyThrows
    @PostMapping("/create_post/{cat_id}")
    public ResponseEntity<String> createPost(@RequestBody PostRequestPayload postRequestPayload, @PathVariable("cat_id") Long catId)  {
        var response = postService.createPost(postRequestPayload, catId);
        return ResponseEntity.ok().body(response);
    }
    @PutMapping("/edit_post/{post_id}")
    public ResponseEntity<String> editPost(@RequestBody PostRequestPayload postRequestPayload, @PathVariable("post_id") Long postId) throws ResourceNotFoundException, NotAuthorizedException {
        var response = postService.editPost(postRequestPayload, postId);
        return ResponseEntity.ok().body(response);
    }
}
