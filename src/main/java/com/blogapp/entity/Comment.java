package com.blogapp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
