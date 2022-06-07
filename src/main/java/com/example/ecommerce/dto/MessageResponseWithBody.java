package com.example.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class MessageResponseWithBody extends MessageResponse{
    private Object respBody;

    public MessageResponseWithBody(String s, Object obj) {
        super(s);
        this.respBody = obj;
    }
}
