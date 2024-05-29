package com.waa.assignment1.posts.repository;

import com.waa.assignment1.posts.entity.Post;
import com.waa.assignment1.posts.service.PostService;
import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private static List<Post> posts;
    private static int postId = 1;

    static {
        posts = new ArrayList<>();
        posts.add(new Post(postId++, "Importance of WAA", "This is my first post", "Yogen"));
        posts.add(new Post(postId++, "Importance of WAP", "This is my second post", "Dikshya"));
        posts.add(new Post(postId++, "Importance of SWE", "This is my third post", "Henry"));
    }
    @Override
    public void save(PostDetailDto p) {

    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public PostDetailDto getById(int id) {
        return null;
    }

    @Override
    public void update(int id, PostDetailDto p) {

    }

    @Override
    public void delete(int id) {

    }
}
