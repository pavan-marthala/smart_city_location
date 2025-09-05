package com.smartcity.location.city;

import com.smartcity.models.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cities")
@Slf4j
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;

    @GetMapping
    public Flux<City> getAll() {
        return cityService.get();
    }

    @GetMapping("/{id}")
    public Mono<City> getById(@PathVariable String id) {
        return cityService.getById(id);
    }

    @PostMapping
    public Mono<String> create(@RequestParam("name") String name) {
        return cityService.create(name);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return cityService.delete(id);
    }

    @PatchMapping("/{id}")
    public Mono<Void> update(@PathVariable String id, @RequestParam("name") String name) {
        return cityService.update(id, name);
    }

}
