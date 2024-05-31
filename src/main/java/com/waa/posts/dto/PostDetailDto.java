package com.waa.posts.dto;

import com.waa.users.dto.UserDto;
import lombok.Data;

@Data
public class PostDetailDto {
    long id;
    String title;
    String content;
    UserDto author;
}
