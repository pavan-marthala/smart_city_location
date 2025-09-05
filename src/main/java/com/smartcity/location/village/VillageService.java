package com.smartcity.location.village;

import com.smartcity.location.city.CityRepository;
import com.smartcity.models.Village;
import com.smartcity.models.VillageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class VillageService {
    private final VillageRepository villageRepository;
    private final CityRepository cityRepository;

    public Flux<Village> getByCityId(String cityId) {
        return villageRepository.findByCityId(cityId).map(VillageMapper.INSTANCE::toModel);
    }

    public Flux<Village> getAll() {
        return villageRepository.findAll().map(VillageMapper.INSTANCE::toModel);
    }

    public Mono<Village> getById(String id) {
        return getVillage(id)
                .map(VillageMapper.INSTANCE::toModel);
    }

    private Mono<VillageEntity> getVillage(String id) {
        return villageRepository.findById(id)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Village not found with id: " + id)));
    }

    public Mono<String> create(VillageRequest villageRequest) {
        return cityRepository.findById(villageRequest.getCityId().toString())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("City not found with id: " + villageRequest.getCityId())))
                .flatMap(cityEntity -> {
            log.info("Creating village {} in city {}", villageRequest.getName(), cityEntity.getName());
            VillageEntity villageEntity = VillageEntity.builder().id(UUID.randomUUID().toString()).name(villageRequest.getName()).cityId(villageRequest.getCityId().toString()).build();
            return villageRepository.save(villageEntity).map(VillageEntity::getId);
        });
    }

    public Mono<Void> delete(String id) {
        return getVillage(id).then(villageRepository.deleteById(id));
    }

    public Mono<Void> update(String id, VillageRequest villageRequest) {
        return getVillage(id)
                .flatMap(existingVillage -> {
                    existingVillage.setName(villageRequest.getName());
                    return villageRepository.save(existingVillage);
                }).then();
    }
}
