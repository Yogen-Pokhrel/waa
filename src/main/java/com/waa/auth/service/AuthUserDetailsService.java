package com.waa.auth.service;

import com.waa.users.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class AuthUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepo;

    public AuthUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByEmail(username);
        var userDetails = new AuthUserDetails(user);
        return userDetails;
    }

}
