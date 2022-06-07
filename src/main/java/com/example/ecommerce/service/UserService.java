package com.example.ecommerce.service;

import com.example.ecommerce.dto.UserFilterReq;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.ERole;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    public User findByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(() -> new NotFoundException("user not found"));
    }

    public List<User> getAllUsers(UserFilterReq userFilterReq) {
        if(userFilterReq.getRole() != null){
            Set<ERole> set = new HashSet<>();
            set.add(userFilterReq.getRole());
            return userRepo.findDistinctByRolesNameIn(set);
        }
        return userRepo.findAll();
    }
}
