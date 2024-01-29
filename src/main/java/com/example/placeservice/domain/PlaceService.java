package com.example.placeservice.domain;

import com.example.placeservice.api.PlaceRequest;
import com.github.slugify.Slugify;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PlaceService {
    private PlaceRepository placeRepository;
    private Slugify slg;

    public PlaceService(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
        this.slg = Slugify.builder().build();
    }

    public Mono<Place> create(PlaceRequest place) {
        var newPlace = new Place(null, place.name(), slg.slugify(place.name()), place.state(), null, null);
        return placeRepository.save(newPlace);
    }

    public Flux<Place> getAll() {
        return placeRepository.findAll();
    }

    public Mono<Place> find(Long id) {
        return placeRepository.findById(id);
    }
}
