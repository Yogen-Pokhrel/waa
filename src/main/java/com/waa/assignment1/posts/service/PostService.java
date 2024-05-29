package com.waa.assignment1.posts.service;


import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;

import java.util.List;

public interface PostService {

    public List<PostDto> findAll();

    PostDetailDto getById(int id);

    void save(PostDetailDto p);

    void delete(int id);

    void update(int id, PostDetailDto p);
}
