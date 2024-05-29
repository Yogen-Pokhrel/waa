package com.waa.assignment1.posts.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    long id;
    String title;
    String content;
    String author;
}
