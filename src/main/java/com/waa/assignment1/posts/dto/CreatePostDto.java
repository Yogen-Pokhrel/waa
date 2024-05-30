package com.waa.assignment1.posts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePostDto {

    @NotBlank(message = "title cannot be empty")
    @Size(min = 2, max = 50, message = "title must be between 2 and 50 characters")
    String title;

    @NotBlank(message = "content cannot be empty")
    @Size(min = 2, max = 500, message = "content must be between 2 and 500 characters")
    String content;

    @NotBlank(message = "author cannot be empty")
    @Size(min = 2, max = 50, message = "author name must be between 2 and 50 characters")
    String author;
}
