package com.waa.comments;

import com.waa.comments.dto.CommentDto;
import com.waa.comments.dto.CreateCommentDto;
import com.waa.comments.dto.UpdateCommentDto;
import com.waa.comments.service.CommentService;
import com.waa.helper.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ApiResponse<CommentDto>> createComment(@Valid @RequestBody CreateCommentDto commentData){
        try{
            CommentDto post = commentService.save(commentData);
            return ResponseEntity.ok(ApiResponse.success(post, "Comment added Successfully"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentDto>> deleteCommentById(@PathVariable("id") @NonNull long id){
        try{
            CommentDto deletedPost = commentService.delete(id);
            return ResponseEntity.ok(ApiResponse.success(deletedPost, "Comment deleted successfully"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<CommentDto>> updateComment(@PathVariable("id") @NonNull long id, @Valid @RequestBody UpdateCommentDto commentData){
        try{
            CommentDto post = commentService.update(id, commentData);
            return new ResponseEntity<>(ApiResponse.success(post, "Comment Updated Successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
