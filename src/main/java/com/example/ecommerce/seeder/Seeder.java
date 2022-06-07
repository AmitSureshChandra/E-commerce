package com.example.ecommerce.seeder;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.enums.ERole;
import com.example.ecommerce.repository.CategoryRepo;
import com.example.ecommerce.repository.ItemRepo;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Seeder {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepo catRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ItemRepo itemRepo;

    @PostConstruct
    @Transactional
    void seed(){
        seedRole();
        seedCategory();
        seedUsers();
//        seedItems();
    }

    private void seedItems() {

        User seller = userRepo.findByUsername("seller").get();

        if(!itemRepo.existsByName("Hp Pavilian 15")){
            Item item = new Item();
            item.setPrice(40000f);
            item.setDescription("Office laptop");
            item.setSeller(seller);
            item.setImage("https://m.media-amazon.com/images/I/81VKTDF2ySL._SY450_.jpg");

//            catRepo.findByName("Office".toUpperCase()).get().addItem(item);
//            catRepo.findByName("Electronic".toUpperCase()).get().addItem(item);

            item.setBrand("HP");
            item.setStock(100l);

            itemRepo.save(item);
        }


        if(!itemRepo.existsByName("Drinking Bottol")){
            Item item = new Item();
            item.setPrice(40f);
            item.setDescription("Water Bottol");
            item.setSeller(seller);
            item.setImage("https://m.media-amazon.com/images/I/51+03Dl42dL._SY355_.jpg");

//            catRepo.findByName("home").get().addItem(item);

            item.setBrand("DrinkNow");
            item.setStock(10000l);

            itemRepo.save(item);
        }
    }

    private void seedUsers() {

        if(!userRepo.existsByEmail("admin@email.com")){
            User admin = new User();

            admin.setEmail("admin@email.com");
            admin.setPassword("admin");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));

            Set<Role> roles = new HashSet<>();

            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_SELLER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).get());

            admin.setRoles(roles);

            userRepo.save(admin);
        }

        if(!userRepo.existsByEmail("seller@email.com")){
            User seller = new User();

            seller.setEmail("seller@email.com");
            seller.setPassword("seller");
            seller.setUsername("seller");

            seller.setPassword(passwordEncoder.encode(seller.getPassword()));

            Set<Role> roles = new HashSet<>();

            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_SELLER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).get());

            seller.setRoles(roles);
            userRepo.save(seller);
        }

        if(!userRepo.existsByEmail("user@email.com")){
            User user = new User();

            user.setEmail("user@email.com");
            user.setPassword("user");
            user.setUsername("user");

            user.setPassword(passwordEncoder.encode(user.getPassword()));

            Set<Role> roles = new HashSet<>();

            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_SELLER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).get());

            user.setRoles(roles);
            userRepo.save(user);
        }

        if(!userRepo.existsByEmail("moderator@email.com")){
            User moderator = new User();

            moderator.setEmail("moderator@email.com");
            moderator.setPassword("moderator");
            moderator.setUsername("moderator");

            moderator.setPassword(passwordEncoder.encode(moderator.getPassword()));

            Set<Role> roles = new HashSet<>();

            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            roles.add(roleRepository.findByName(ERole.ROLE_USER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_SELLER).get());
            roles.add(roleRepository.findByName(ERole.ROLE_MODERATOR).get());

            moderator.setRoles(roles);
            userRepo.save(moderator);
        }
    }

    private void seedCategory() {
        Arrays.asList("Home","Office", "Electronic", "Study", "Kitchen").forEach(c -> {
            if(!catRepo.findByName(c.toUpperCase()).isPresent()){
                Category cat = new Category();
                cat.setName(c.toUpperCase());
                cat.setDisplayName(c);
                catRepo.save(cat);
            }
        });
    }


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
