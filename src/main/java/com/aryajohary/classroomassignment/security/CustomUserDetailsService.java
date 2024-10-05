package com.aryajohary.classroomassignment.security;

import com.aryajohary.classroomassignment.repos.UserRepo;
import com.aryajohary.classroomassignment.schemas.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        System.out.println("CustomUserDetailsService called for username: " + username);
        if(user==null){
            System.out.println("username not found");
            throw new UsernameNotFoundException("Username "+username+" not found");
        }
        return new UserPrinciple(user);
    }
}
