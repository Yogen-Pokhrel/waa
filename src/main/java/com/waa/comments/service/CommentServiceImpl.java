package com.waa.comments.service;

import com.waa.comments.CommentRepository;
import com.waa.comments.dto.CommentDto;
import com.waa.comments.dto.CreateCommentDto;
import com.waa.comments.dto.UpdateCommentDto;
import com.waa.comments.entity.Comment;
import com.waa.helper.ListMapper;
import com.waa.posts.repository.PostRepository;
import com.waa.users.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

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
    public CommentDto save(CreateCommentDto p) throws Exception {
        userRepository.findById(p.getAddedById()).orElseThrow(() -> new Exception("No user found with the provided ID"));
        postRepository.findById(p.getPostId()).orElseThrow(() -> new Exception("No post found with the provided ID"));
        return modelMapper.map(commentRepository.save(modelMapper.map(p, Comment.class)), CommentDto.class);
    }

    @Override
    public CommentDto update(long id, UpdateCommentDto p) throws Exception {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new Exception("No comment found with the provided ID"));
        comment.setComment(p.getComment());
        return modelMapper.map(commentRepository.save(comment), CommentDto.class);
    }

    @Override
    public CommentDto delete(long id) throws Exception {
        Comment p = commentRepository.findById(id).orElseThrow(() -> new Exception("No comment found with the provided ID"));
        commentRepository.delete(p);
        return modelMapper.map(p, CommentDto.class);
    }
}
