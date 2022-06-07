package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
public class ItemDetailDto {
    private Long id;
    private String name;
    private String brand;
    private Float price;
    private Long stock;
    private String image;
    private String description;
    private Set<String> categories = new HashSet<>();
    private Long sellerId;
}
