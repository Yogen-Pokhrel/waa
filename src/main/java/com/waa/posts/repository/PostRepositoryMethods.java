package com.waa.posts.repository;

import com.waa.posts.entity.Post;

import java.util.List;

public interface PostRepositoryMethods {
    List<Post> findPosts(String authorName, String title);
}
