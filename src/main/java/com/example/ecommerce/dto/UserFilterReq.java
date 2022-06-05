package com.example.ecommerce.dto;

import com.example.ecommerce.enums.ERole;
import lombok.Data;

@Data
public class UserFilterReq {
    private ERole role;
}
