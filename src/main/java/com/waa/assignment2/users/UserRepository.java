package com.waa.assignment2.users;

import com.waa.assignment2.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE (SELECT COUNT(p) FROM Post p WHERE p.author = u) > :postCount")
    List<User> getUserWithPostsCount(int postCount);
}
