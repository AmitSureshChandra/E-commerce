package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ItemCreateDto;
import com.example.ecommerce.dto.ItemDetailDto;
import com.example.ecommerce.dto.MessageResponse;
import com.example.ecommerce.dto.MessageResponseWithBody;
import com.example.ecommerce.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/items")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<MessageResponse> createItem(@RequestBody @Valid ItemCreateDto dto){
        return new ResponseEntity<>(new MessageResponseWithBody("OK", itemService.save(dto)), HttpStatus.CREATED);
    }

    @GetMapping("/{item_id}")
    public ResponseEntity<ItemDetailDto> getDetail(@PathVariable(name = "item_id") Long itemId){
        return ResponseEntity.ok(itemService.getItemDetail(itemId));
    }

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<ItemDetailDto>> getByCategory(@PathVariable(name = "category") String category){
        return ResponseEntity.ok(itemService.getItemByCategory(category));
    }

}
