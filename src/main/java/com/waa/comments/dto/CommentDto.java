package com.waa.comments.dto;

import com.waa.users.dto.UserDto;
import com.waa.users.entity.User;
import lombok.Data;

@Data
public class CommentDto {
    private long commentId;
    private String comment;
    private UserDto addedBy;
}
