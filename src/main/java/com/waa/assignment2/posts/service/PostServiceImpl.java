package com.waa.assignment2.posts.service;

import com.waa.assignment2.posts.dto.CreatePostDto;
import com.waa.assignment2.posts.dto.PostDetailDto;
import com.waa.assignment2.posts.dto.PostDto;
import com.waa.assignment2.posts.dto.UpdatePostDto;
import com.waa.assignment2.posts.entity.Post;
import com.waa.assignment2.posts.PostRepository;
import com.waa.assignment2.users.UserRepository;
import com.waa.assignment2.users.entity.User;
import com.waa.helper.ListMapper;
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

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Override
    public PostDetailDto save(CreatePostDto p) throws Exception {
        userRepository.findById(p.getAuthorId()).orElseThrow(() -> new Exception("No author found with the provided ID"));
        return modelMapper.map(postRepository.save(modelMapper.map(p, Post.class)), PostDetailDto.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PostDto> findAll() {
        return (List<PostDto>) listMapper.mapList(postRepository.findAll(),new PostDto());
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
    public List<PostDto> getPostsByAuthor(String author) {
        return (List<PostDto>) listMapper.mapList(postRepository.findByAuthorName(author),new PostDto());
    }
}
