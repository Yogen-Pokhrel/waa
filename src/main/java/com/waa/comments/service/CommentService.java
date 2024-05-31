package com.waa.comments.service;

import com.waa.comments.dto.CommentDto;
import com.waa.comments.dto.CreateCommentDto;
import com.waa.comments.dto.UpdateCommentDto;

import java.util.List;

public interface CommentService {

    CommentDto save(CreateCommentDto p) throws Exception;

    CommentDto delete(long id) throws Exception;

    CommentDto update(long id, UpdateCommentDto p) throws Exception;
}
