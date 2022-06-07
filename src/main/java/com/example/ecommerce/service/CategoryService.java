package com.example.ecommerce.service;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Set<Category> findByNameIn(Set<String> categories) {
        System.out.println( categoryRepo.findByNameIn(categories));
        return categoryRepo.findByNameIn(categories);
    }
}
