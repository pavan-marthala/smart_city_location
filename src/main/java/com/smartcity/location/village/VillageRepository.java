package com.smartcity.location.village;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VillageRepository extends ReactiveCrudRepository<VillageEntity,String> {
    Flux<VillageEntity> findByCityId(String cityId);
}
