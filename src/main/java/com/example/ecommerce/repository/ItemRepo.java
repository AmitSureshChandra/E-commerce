package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item, Long> {
    List<Item> findByCategoriesName(String toUpperCase);

    boolean existsByName(String s);
}
