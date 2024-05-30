package com.waa.assignment2.posts;

import com.waa.assignment2.posts.dto.CreatePostDto;
import com.waa.assignment2.posts.dto.PostDetailDto;
import com.waa.assignment2.posts.dto.PostDto;
import com.waa.assignment2.posts.dto.UpdatePostDto;
import com.waa.assignment2.posts.service.PostService;
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
        try{
            return ResponseEntity.ok(ApiResponse.success(postService.findAll(), "Posts fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error("Posts fetched successfully!"),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PostDto>>> getPostsMadeByAuthor(@DefaultValue("") @RequestParam String author){
        return ResponseEntity.ok(ApiResponse.success(postService.getPostsByAuthor(author), "Post fetched successfully!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDetailDto>> getPostById(@PathVariable long id){
        try {
            PostDetailDto post = postService.getById(id);
            return ResponseEntity.ok(ApiResponse.success(post, "Post fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ApiResponse<PostDetailDto>> createPost(@Valid @RequestBody CreatePostDto postData){
        try{
            PostDetailDto post = postService.save(postData);
            return ResponseEntity.ok(ApiResponse.success(post, "Post created Successfully"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDto>> deletePostById(@PathVariable("id") @NonNull long id){
        try{
            PostDto deletedPost = postService.delete(id);
            return ResponseEntity.ok(ApiResponse.success(deletedPost, "Post deleted successfully"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<PostDetailDto>> updatePost(@PathVariable("id") @NonNull long id, @Valid @RequestBody UpdatePostDto postData){
        try{
            PostDetailDto post = postService.update(id, postData);
            return new ResponseEntity<>(ApiResponse.success(post, "Post Updated Successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
