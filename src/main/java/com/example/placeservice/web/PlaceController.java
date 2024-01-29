package com.example.placeservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.placeservice.api.PlaceRequest;
import com.example.placeservice.api.PlaceResponse;
import com.example.placeservice.domain.Place;
import com.example.placeservice.domain.PlaceService;

import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/places")
public class PlaceController {
    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }
    
    @PostMapping
    public ResponseEntity<Mono<PlaceResponse>> create(@Valid @RequestBody PlaceRequest place) {
        System.out.println(place);
        var placeResponse = placeService.create(place).map(PlaceMapper::FormatPlaceCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(placeResponse);
    }

    @GetMapping
    public ResponseEntity<Flux<Place>> getAll() {
        var all = placeService.getAll();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(all);
    }

    @GetMapping("{id}")
  public ResponseEntity<Mono<Place>> get(@PathVariable("id") Long id) {
        var place = placeService.find(id);

        System.out.println(place);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(place);
    }
    
}
