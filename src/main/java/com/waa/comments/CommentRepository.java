package com.waa.comments;

import com.waa.comments.entity.Comment;
import com.waa.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.post = :post")
    List<Comment> findCommentsByPostId(Post post);
}
