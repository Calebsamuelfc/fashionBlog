package com.kay.fashion_blog.controller;

import com.kay.fashion_blog.dto.CommentDto;
import com.kay.fashion_blog.entity.Comments;
import com.kay.fashion_blog.exceptions.NotAuthorizedException;
import com.kay.fashion_blog.exceptions.ResourceNotFoundException;
import com.kay.fashion_blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PutMapping("/edit_comment/{comment_id}")
    public ResponseEntity<String> editComment(@RequestBody CommentDto commentDto, @PathVariable("comment_id") Long commentId) throws NotAuthorizedException, ResourceNotFoundException {
        var response = userService.editComment(commentDto, commentId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create_comment/{postId}")
    public ResponseEntity<String> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Long postId) throws ResourceNotFoundException {
        var response = userService.createComment(commentDto, postId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/like_post/{postId}")
    public ResponseEntity<String> likePost(@PathVariable ("postId") Long postId) throws ResourceNotFoundException {
        var response = userService.likePost(postId);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/view_comments/{postId}")
    public ResponseEntity<List<Comments>> viewComments(@PathVariable ("postId") Long postId) throws ResourceNotFoundException {
        var response = userService.viewAllComments(postId);
        return ResponseEntity.ok().body(response);
    }


}
