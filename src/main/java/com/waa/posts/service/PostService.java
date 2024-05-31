package com.waa.posts.service;


import com.waa.comments.dto.CommentDto;
import com.waa.posts.dto.CreatePostDto;
import com.waa.posts.dto.PostDetailDto;
import com.waa.posts.dto.PostDto;
import com.waa.posts.dto.UpdatePostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAll();

    PostDetailDto getById(long id) throws Exception;

    PostDetailDto save(CreatePostDto p) throws Exception;

    PostDto delete(long id) throws Exception;

    PostDetailDto update(long id, UpdatePostDto p) throws Exception;

    List<PostDto> getPostsByFilter(String author, String title);

    List<CommentDto> getCommentsByPostId(long postId) throws Exception;
}
