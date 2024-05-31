package com.waa.users.service;

import com.waa.posts.dto.PostDto;
import com.waa.users.dto.CreateUserDto;
import com.waa.users.dto.UpdateUserDto;
import com.waa.users.dto.UserDetailDto;
import com.waa.users.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDetailDto getById(long id) throws Exception;

    UserDetailDto save(CreateUserDto p);

    UserDto delete(long id) throws Exception;

    UserDetailDto update(long id, UpdateUserDto p) throws Exception;

    List<PostDto> getPosts(long id) throws Exception;

    List<UserDto> getUserWithPostsCount(int postCount);
}
