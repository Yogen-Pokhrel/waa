package com.waa.assignment1.posts.dto;

import lombok.Data;

@Data
public class PostDetailDto {
    long id;
    String title;
    String content;
    String author;
}
