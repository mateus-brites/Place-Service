package com.example.placeservice.web;

import com.example.placeservice.api.PlaceResponse;
import com.example.placeservice.domain.Place;

public class PlaceMapper {
    public static PlaceResponse FormatPlaceCreate(Place place) {
        return new PlaceResponse(place.name(), place.slug(), place.state(), place.created_at(), place.updated_at());
    }
    
}
