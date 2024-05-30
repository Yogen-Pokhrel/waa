package com.waa.assignment1.posts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePostDto {
    String title;
    String content;
    String author;
}
