package com.waa.assignment1.posts.service;

import com.waa.assignment1.posts.dto.PostDetailDto;
import com.waa.assignment1.posts.dto.PostDto;
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
    public void save(PostDetailDto p) {

    }

    @Override
    public List<PostDto> findAll() {
        return (List<PostDto>) listMapper.mapList(postRepository.findAll(),new PostDto());
    }

    @Override
    public PostDetailDto getById(int id) {
        return null;
    }

    @Override
    public void update(int id, PostDetailDto p) {

    }

    @Override
    public void delete(int id) {

    }
}
