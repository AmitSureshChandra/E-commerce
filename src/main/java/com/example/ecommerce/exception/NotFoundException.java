package com.example.ecommerce.exception;

public class NotFoundException extends  RuntimeException{
    public NotFoundException(String msg){
        super(msg);
    }
}
