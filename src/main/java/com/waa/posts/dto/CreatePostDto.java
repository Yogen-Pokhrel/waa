package com.waa.posts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "authorId cannot be empty")
    Long authorId;
}
