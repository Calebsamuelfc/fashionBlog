package com.kay.fashion_blog.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categories extends BaseClass{
    private String text;
}
