package com.waa.assignment1.posts.repository;

import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;
import com.waa.assignment1.posts.entity.Post;

import java.util.List;

public interface PostRepository {
    public List<Post> findAll();

    PostDetailDto getById(int id);

    void save(PostDetailDto p);

    void delete(int id);

    void update(int id, PostDetailDto p);
}
