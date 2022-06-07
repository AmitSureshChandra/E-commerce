package com.example.ecommerce.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Set;

@Data
public class ItemCreateDto {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String brand;

    @NotNull
    @Positive
    private Float price;

    @NotNull
    @Positive
    private Long stock;

    @NotNull
    @NotBlank
    private String image;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotEmpty
    private Set<String> categories;
}
