package com.waa.assignment1.posts.service;


import com.waa.assignment1.posts.dto.CreatePostDto;
import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;
import com.waa.assignment1.posts.dto.UpdatePostDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAll();

    PostDetailDto getById(long id);

    PostDetailDto save(CreatePostDto p);

    PostDto delete(long id);

    PostDetailDto update(long id, UpdatePostDto p);

    List<PostDto> getPostsByAuthor(String author);
}
