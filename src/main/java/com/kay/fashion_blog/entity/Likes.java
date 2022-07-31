package com.kay.fashion_blog.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Likes extends BaseClass{
    @ManyToOne
    private Users users;
}
