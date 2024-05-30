package com.waa.assignment1.posts;

import com.waa.assignment1.posts.dto.CreatePostDto;
import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;
import com.waa.assignment1.posts.dto.UpdatePostDto;
import com.waa.assignment1.posts.entity.Post;
import com.waa.assignment1.posts.service.PostService;
import com.waa.helper.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ApiResponse<List<PostDto>>> getPosts(){
        return ResponseEntity.ok(ApiResponse.success(postService.findAll(), "Posts fetched successfully!"));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PostDto>>> getPostsMadeByAuthor(@DefaultValue("") @RequestParam String author){
        return ResponseEntity.ok(ApiResponse.success(postService.getPostsByAuthor(author), "Post fetched successfully!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDetailDto>> getPostById(@PathVariable long id){
        return ResponseEntity.ok(ApiResponse.success(postService.getById(id), "Post fetched successfully!"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostDetailDto>> createPost(@Valid @RequestBody CreatePostDto postData){
        PostDetailDto post = postService.save(postData);
        if(post == null) {
            return new ResponseEntity<>(ApiResponse.error("Error while creating post"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ApiResponse.success(post, "Post created Successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> deletePostById(@PathVariable("id") @NonNull long id){
        PostDto deletedPost = postService.delete(id);
        if(deletedPost == null) {
            return new ResponseEntity<>(ApiResponse.error("No post found with the provided id"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ApiResponse.success(deletedPost, "Post deleted successfully"), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDetailDto>> updatePost(@PathVariable("id") @NonNull long id, @Valid @RequestBody UpdatePostDto postData){
        if(postService.getById(id) == null){
            return new ResponseEntity<>(ApiResponse.error("No post found with the provided id"), HttpStatus.BAD_REQUEST);
        }
        PostDetailDto post = postService.update(id, postData);
        if(post == null) {
            return new ResponseEntity<>(ApiResponse.error("Error while updating post"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ApiResponse.success(post, "Post Updated Successfully"), HttpStatus.CREATED);
    }

}
