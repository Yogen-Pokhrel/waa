package com.waa.posts.service;

import com.waa.comments.CommentRepository;
import com.waa.comments.dto.CommentDto;
import com.waa.posts.dto.CreatePostDto;
import com.waa.posts.dto.PostDetailDto;
import com.waa.posts.dto.PostDto;
import com.waa.posts.dto.UpdatePostDto;
import com.waa.posts.entity.Post;
import com.waa.posts.repository.PostRepository;
import com.waa.users.UserRepository;
import com.waa.users.entity.User;
import com.waa.helper.ListMapper;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Autowired
    EntityManager entityManager;


    @Override
    public PostDetailDto save(CreatePostDto p) throws Exception {
        userRepository.findById(p.getAuthorId()).orElseThrow(() -> new Exception("No author found with the provided ID"));
        return modelMapper.map(postRepository.save(modelMapper.map(p, Post.class)), PostDetailDto.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PostDto> findAll() {
        List<Post> posts = entityManager.createQuery(
                        "SELECT p FROM Post p JOIN FETCH p.author", Post.class)
                .getResultList();
        return listMapper.mapList(posts, new PostDto());
    }

    @Override
    public PostDetailDto getById(long id) throws Exception {
        Post p = postRepository.findById(id).orElseThrow(() -> new Exception("No post found with the provided ID"));
        return modelMapper.map(p, PostDetailDto.class);
    }

    @Override
    public PostDetailDto update(long id, UpdatePostDto p) throws Exception {
        Post post = postRepository.findById(id).orElseThrow(() -> new Exception("No post found with the provided ID"));
        post.setTitle(p.getTitle());
        post.setContent(p.getContent());
        if(p.getAuthorId() != 0){
            User u = userRepository.findById(p.getAuthorId()).orElseThrow(() -> new Exception("No author found with the provided ID"));
            post.setAuthor(u);
        }
        return modelMapper.map(postRepository.save(post), PostDetailDto.class);
    }

    @Override
    public PostDto delete(long id) throws Exception {
        Post p = postRepository.findById(id).orElseThrow(() -> new Exception("No post found with the provided ID"));
        postRepository.delete(p);
        return modelMapper.map(p, PostDto.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PostDto> getPostsByFilter(String author, String title) throws Exception {
        //uncomment this to test the exception catch and record in exception table
//        throw new Exception("Test exception");
        return (List<PostDto>) listMapper.mapList(postRepository.findPosts(author, title),new PostDto());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<CommentDto> getCommentsByPostId(long postId) throws Exception {
        Post p = postRepository.findById(postId).orElseThrow(() -> new Exception("No post found with the provided ID"));
        return (List<CommentDto>) listMapper.mapList(commentRepository.findCommentsByPostId(p), new CommentDto());
    }
}
