package com.waa.users.dto;

import com.waa.posts.dto.PostDto;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private long id;
    private String name;
    private String email;
}
