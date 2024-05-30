package com.waa.assignment1.posts.repository;

import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;
import com.waa.assignment1.posts.entity.Post;

import java.util.List;

public interface PostRepository {
    public List<Post> findAll();

    Post getById(long id);

    Post save(Post p);

    Post delete(long id);

    Post update(long id, Post p);

    List<Post> getPostsByAuthor(String author);
}
