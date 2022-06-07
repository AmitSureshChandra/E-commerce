package com.example.ecommerce.service;

import com.example.ecommerce.dto.ItemCreateDto;
import com.example.ecommerce.dto.ItemDetailDto;
import com.example.ecommerce.entity.Category;
import com.example.ecommerce.entity.Item;
import com.example.ecommerce.exception.NotFoundException;
import com.example.ecommerce.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private UtilService utilService;

    @Autowired
    private CategoryService categoryService;

    public Item save(ItemCreateDto dto) {

        Item item = new Item();

        item.setDescription(dto.getDescription());
        item.setName(dto.getName());
        item.setImage(dto.getImage());
        item.setBrand(dto.getBrand());
        item.setPrice(dto.getPrice());
        item.setStock(dto.getStock());
        item.addCategories(categoryService.findByNameIn(dto.getCategories()));
        item.setSeller(userService.findByUsername(utilService.getAuthUsername()));

        return itemRepo.save(item);
    }

    public Item getItem(Long id){
        return itemRepo.findById(id).orElseThrow(() -> new NotFoundException("item not found"));
    }

    public ItemDetailDto getItemDetail(Long itemId) {
        Item item = getItem(itemId);
        return getItemDetailByItem(item);
    }

    private ItemDetailDto getItemDetailByItem(Item item) {
        ItemDetailDto dto = new ItemDetailDto();

        dto.setId(item.getId());
        dto.setBrand(item.getBrand());
        dto.setImage(item.getImage());
        dto.setDescription(item.getDescription());
        dto.setStock(item.getStock());
        dto.setPrice(item.getPrice());
        dto.setCategories(item.getCategories().stream().map(Category::getDisplayName).collect(Collectors.toSet()));
        dto.setSellerId(item.getSeller().getId());
        dto.setName(item.getName());

        return dto;
    }

    public List<ItemDetailDto> getItemByCategory(String category) {
        return itemRepo.findByCategoriesName(category.toUpperCase()).stream().map(this::getItemDetailByItem).collect(Collectors.toList());
    }
}
