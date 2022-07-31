package com.kay.fashion_blog.entity;

import com.kay.fashion_blog.constants.Roles;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseClass{
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
//    @OneToMany
//    private Posts posts;
//    @OneToMany
//    private Comments comments;
}
