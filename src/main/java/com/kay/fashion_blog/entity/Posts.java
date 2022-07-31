package com.kay.fashion_blog.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Posts extends BaseClass{
    private String title;
    private String body;
    @ManyToOne
    private Categories categories;
    @ManyToOne
    private Users users;
    @OneToMany
    private List<Comments> comments;
    @OneToMany
    private List<Likes> likes;
}
