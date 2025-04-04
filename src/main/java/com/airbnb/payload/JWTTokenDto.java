package com.airbnb.payload;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class JWTTokenDto {

    private String token;
    private String type;
}
