package com.smartcity.location.village;

import com.smartcity.models.Village;
import com.smartcity.models.VillageRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/villages")
@RequiredArgsConstructor
public class VillageController {
    private final VillageService villageService;

    @GetMapping
    public Flux<Village> getAll(@RequestParam(required = false) String cityId) {
        return cityId == null ? villageService.getAll() : villageService.getByCityId(cityId);
    }
    @GetMapping("/{id}")
    public Mono<Village> getById(@PathVariable String id) {
        return villageService.getById(id);
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public Mono<String> create(@RequestBody @Valid VillageRequest villageRequest) {
        return villageService.create(villageRequest);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public Mono<Void> delete(@PathVariable String id) {
        return villageService.delete(id);
    }
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public Mono<Void> update(@PathVariable String id, @RequestBody VillageRequest villageRequest) {
        return villageService.update(id, villageRequest);
    }
}
