package com.kay.fashion_blog.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private Long id;

    @CreationTimestamp
    private LocalDateTime timeCreated;

    @CreationTimestamp
    private LocalDateTime timeUpdated;
}
