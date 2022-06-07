package com.example.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String brand;

    private Float price;

    private Long stock;

    private String image;
    private String description;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "item_category",
            joinColumns = @JoinColumn(name = "item_id")  ,
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonBackReference
    private User seller;

    public void addCategory(Category category){
        this.categories.add(category);
        category.getItems().add(this);
    }

    public void addCategories(Collection<Category> categories){
        this.categories.addAll(categories);
        categories.forEach(category -> category.getItems().add(this));
    }
}
