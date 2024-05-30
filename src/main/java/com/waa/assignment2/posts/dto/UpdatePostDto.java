package com.waa.assignment2.posts.dto;

import lombok.Data;

@Data
public class UpdatePostDto {
    String title;
    String content;
    long authorId;
}
