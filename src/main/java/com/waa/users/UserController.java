package com.waa.users;

import com.waa.posts.dto.PostDto;
import com.waa.users.dto.CreateUserDto;
import com.waa.users.dto.UpdateUserDto;
import com.waa.users.dto.UserDetailDto;
import com.waa.users.dto.UserDto;
import com.waa.users.service.UserService;
import com.waa.helper.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserDto>>> getUsers(){
        return ResponseEntity.ok(ApiResponse.success(userService.findAll(), "Users fetched successfully!"));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserDto>>> getUsersWithFilters(@RequestParam(required = true) Integer minPostCount){
        System.out.println("Min count: " + minPostCount);
        return ResponseEntity.ok(ApiResponse.success(userService.getUserWithPostsCount(minPostCount), "Users fetched successfully!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDetailDto>> getUserById(@PathVariable long id){
        try{
            UserDetailDto u = userService.getById(id);
            return ResponseEntity.ok(ApiResponse.success(u, "User fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/posts")
    public ResponseEntity<ApiResponse<List<PostDto>>> getUserPostsByUserId(@PathVariable long id){
        try{
            List<PostDto> allPosts = userService.getPosts(id);
            return ResponseEntity.ok(ApiResponse.success(allPosts, "User posts fetched successfully!"));
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserDetailDto>> createUser(@Valid @RequestBody CreateUserDto userData){
       try{
           UserDetailDto user = userService.save(userData);
           return new ResponseEntity<>(ApiResponse.success(user, "User created Successfully"), HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDto>> deleteUserById(@PathVariable("id") @NonNull long id){
        try{
            UserDto deletedUser = userService.delete(id);
            return new ResponseEntity<>(ApiResponse.success(deletedUser, "User deleted successfully"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDetailDto>> updateUser(@PathVariable("id") @NonNull long id, @Valid @RequestBody UpdateUserDto userData){
        try{
            UserDetailDto user = userService.update(id, userData);
            return new ResponseEntity<>(ApiResponse.success(user, "User Updated Successfully"), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
