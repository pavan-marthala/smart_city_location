package com.smartcity.location.city;

import org.mapstruct.Mapper;
import com.smartcity.models.City;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CityMapper {
    public static CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);
    City toDomain(CityEntity cityEntity);
    CityEntity toEntity(City city);
}
