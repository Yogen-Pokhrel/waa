package com.waa.assignment2.posts.service;


import com.waa.assignment2.posts.dto.CreatePostDto;
import com.waa.assignment2.posts.dto.PostDetailDto;
import com.waa.assignment2.posts.dto.PostDto;
import com.waa.assignment2.posts.dto.UpdatePostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAll();

    PostDetailDto getById(long id) throws Exception;

    PostDetailDto save(CreatePostDto p) throws Exception;

    PostDto delete(long id) throws Exception;

    PostDetailDto update(long id, UpdatePostDto p) throws Exception;

    List<PostDto> getPostsByAuthor(String author);
}
