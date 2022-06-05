package com.example.ecommerce.seeder;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.enums.ERole;
import com.example.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class Seeder {

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    void seedRole(){
        Arrays.asList(ERole.ROLE_ADMIN, ERole.ROLE_USER, ERole.ROLE_MODERATOR, ERole.ROLE_SELLER).forEach(r -> {
            if(!roleRepository.findByName(r).isPresent()){
                Role role = new Role();
                role.setName(r);
                roleRepository.save(role);
            }
        });
    }
}
