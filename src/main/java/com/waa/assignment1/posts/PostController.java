package com.waa.assignment1.posts;

import com.waa.assignment1.posts.dto.PostDto;
import com.waa.assignment1.posts.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> getPosts(){
        return postService.findAll();
    }

}