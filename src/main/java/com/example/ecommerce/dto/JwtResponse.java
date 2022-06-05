package com.example.ecommerce.dto;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type;
    private Long id;
    private String username;
    private String email;
    private  List<String>  roles;

    public JwtResponse(String jwt, Long id, String username, String email, List<String> roles) {
        token = jwt;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
        type = "bearer";
    }
}
