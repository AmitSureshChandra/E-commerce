package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String displayName;

    @JsonBackReference
    @ManyToMany(mappedBy = "categories",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<Item> items = new HashSet<>();
}
