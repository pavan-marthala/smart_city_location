package com.smartcity.location.city;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveCrudRepository<CityEntity,String> {
}
