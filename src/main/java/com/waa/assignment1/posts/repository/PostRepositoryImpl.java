package com.waa.assignment1.posts.repository;

import com.waa.assignment1.posts.dto.PostDto;
import com.waa.assignment1.posts.entity.Post;
import com.waa.assignment1.posts.dto.PostDetailDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Post save(Post p) {
        p.setId(postId++);
        posts.add(p);
        return p;
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public List<Post> getPostsByAuthor(String author) {
        return posts.stream().filter(x -> x.getAuthor().toLowerCase().contains(author.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public Post getById(long id) {
        return posts
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Post update(long id, Post p) {
        Post toUpdate = getById(id);
        if(p.getTitle() != null) toUpdate.setTitle(p.getTitle());
        if(p.getAuthor() != null) toUpdate.setAuthor(p.getAuthor());
        if(p.getContent() != null) toUpdate.setContent(p.getContent());
        return toUpdate;
    }

    @Override
    public Post delete(long id) {
        Post post = posts
                .stream()
                .filter(l -> l.getId() == id)
                .findFirst().orElse(null);
        System.out.println("Post found" + post);
        if(post != null) {posts.remove(post);}
        return post;
    }
}
