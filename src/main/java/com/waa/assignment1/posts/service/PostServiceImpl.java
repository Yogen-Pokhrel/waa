package com.waa.assignment1.posts.service;

import com.waa.assignment1.posts.dto.CreatePostDto;
import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;
import com.waa.assignment1.posts.dto.UpdatePostDto;
import com.waa.assignment1.posts.entity.Post;
import com.waa.assignment1.posts.repository.PostRepository;
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

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Override
    public PostDetailDto save(CreatePostDto p) {
      return modelMapper.map(postRepository.save(modelMapper.map(p, Post.class)), PostDetailDto.class);
    }

    @Override
    public List<PostDto> findAll() {
        return (List<PostDto>) listMapper.mapList(postRepository.findAll(),new PostDto());
    }

    @Override
    public PostDetailDto getById(long id) {
        Post p = postRepository.getById(id);
        if(p == null) {return null;}
        return modelMapper.map(p, PostDetailDto.class);
    }

    @Override
    public PostDetailDto update(long id, UpdatePostDto p) {
        return modelMapper.map(postRepository.update(id, modelMapper.map(p, Post.class)), PostDetailDto.class);
    }

    @Override
    public PostDto delete(long id) {
        Post deletedPost = postRepository.delete(id);
        if(deletedPost != null) {
            return modelMapper.map(deletedPost, PostDto.class);
        }
        return null;
    }

    @Override
    public List<PostDto> getPostsByAuthor(String author) {
        return (List<PostDto>) listMapper.mapList(postRepository.getPostsByAuthor(author),new PostDto());
    }
}
