package com.waa.assignment2.posts.dto;

import com.waa.assignment2.users.dto.UserDto;
import com.waa.assignment2.users.entity.User;
import lombok.Data;

@Data
public class PostDetailDto {
    long id;
    String title;
    String content;
    UserDto author;
}
