package com.waa.users.service;

import com.waa.posts.repository.PostRepository;
import com.waa.posts.dto.PostDto;
import com.waa.users.UserRepository;
import com.waa.users.dto.CreateUserDto;
import com.waa.users.dto.UpdateUserDto;
import com.waa.users.dto.UserDetailDto;
import com.waa.users.dto.UserDto;
import com.waa.users.entity.User;
import com.waa.helper.ListMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;
    @Autowired
    private PostRepository postRepository;

    @Override
    @SuppressWarnings("unchecked")
    public List<UserDto> findAll() {
        return (List<UserDto>) listMapper.mapList(userRepository.findAll(),new UserDto());

    }

    @Override
    public UserDetailDto getById(long id) throws Exception{
        User p = userRepository.findById(id).orElseThrow(() -> new Exception("No user found with provided id"));
        return modelMapper.map(p, UserDetailDto.class);
    }

    @Override
    public UserDetailDto update(long id, UpdateUserDto p) throws Exception{
        User user = userRepository.findById(id).orElseThrow(() -> new Exception("No user found with provided id"));
        user.setEmail(p.getEmail());
        user.setName(p.getName());
        return modelMapper.map(userRepository.save(user), UserDetailDto.class);
    }

    @Override
    public UserDto delete(long id) throws Exception {
        User p = userRepository.findById(id).orElseThrow(() -> new Exception("No user found with provided Id"));
        userRepository.delete(p);
        return modelMapper.map(p, UserDto.class);
    }

    @Override
    public UserDetailDto save(CreateUserDto p) {
        return modelMapper.map(userRepository.save(modelMapper.map(p, User.class)), UserDetailDto.class);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PostDto> getPosts(long id) throws Exception {
        User u = userRepository.findById(id).orElseThrow(() -> new Exception("No user found with provided Id"));
        return (List<PostDto>) listMapper.mapList(postRepository.findByAuthor(u),new PostDto());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserDto> getUserWithPostsCount(int postCount) {
        return (List<UserDto>) listMapper.mapList(userRepository.getUserWithPostsCount(postCount),new UserDto());
    }
}
