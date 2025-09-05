package com.smartcity.location.city;

import com.smartcity.location.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import com.smartcity.models.City;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    public Flux<City> get(){
        return cityRepository.findAll().map(CityMapper.INSTANCE::toDomain);
    }
    public Mono<City> getById(String id){
        return getCityMono(id)
                .map(CityMapper.INSTANCE::toDomain);
    }

    private Mono<CityEntity> getCityMono(String id) {
        return cityRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("City not found with id: " + id)));
    }

    public Mono<String > create(String name){
        CityEntity cityEntity =  CityEntity.builder().id(UUID.randomUUID().toString()).name(name).build();
        return r2dbcEntityTemplate.insert(CityEntity.class).using(cityEntity)
                .map(CityEntity::getId);
    }
    public Mono<Void> delete(String id){
        return getById(id).then(cityRepository.deleteById(id));
    }
    public Mono<Void> update(String id, String name){
        return getCityMono(id)
                .flatMap(existingCity -> {
                    existingCity.setName(name);
                    return cityRepository.save(existingCity);
                }).then();
    }

}
