package com.waa.posts.repository;

import com.waa.posts.entity.Post;
import com.waa.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryMethods {
    @Query("SELECT p FROM Post p WHERE p.author.name LIKE %:author%")
    List<Post> findByAuthorName(String author);

    @Query("SELECT p FROM Post p WHERE p.author = :author")
    List<Post> findByAuthor(User author);
}
