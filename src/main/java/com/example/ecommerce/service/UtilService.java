package com.example.ecommerce.service;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UtilService {

    @Autowired
    private UserService userService;


    public String getAuthUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails)auth.getPrincipal()).getUsername();
    }

    public User getAuthUser(){
        return userService.findByUsername(getAuthUsername());
    }

    public Long getAuthUserId(){
        return userService.findByUsername(getAuthUsername()).getId();
    }
}

