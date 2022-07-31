package com.kay.fashion_blog.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponsePayload {
    private String customerName;
    private String content;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;
}
