package com.kay.fashion_blog.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestPayload {
    private String title;
    private String content;
}
