package com.waa.comments.dto;

import lombok.Data;

@Data
public class CreateCommentDto {
    private String comment;
    private long addedById;
    private long postId;
}
