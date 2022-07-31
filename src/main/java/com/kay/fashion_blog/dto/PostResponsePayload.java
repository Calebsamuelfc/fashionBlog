package com.kay.fashion_blog.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostResponsePayload {
    private String title;
    private String content;
    private String categories;
    private String author;
    private Integer numberOfLikes;
    private List<String> likedBy;
    private List<CommentResponsePayload> commentsList;

}
