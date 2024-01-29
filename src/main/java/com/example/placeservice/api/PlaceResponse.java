package com.example.placeservice.api;

import java.time.LocalDateTime;

public record PlaceResponse(
    String name, String slug, String state, LocalDateTime created_at, LocalDateTime updated_at
) {
    
}
