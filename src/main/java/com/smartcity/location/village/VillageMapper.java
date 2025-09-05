package com.smartcity.location.village;

import com.smartcity.models.Village;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VillageMapper {
    public static VillageMapper INSTANCE = Mappers.getMapper(VillageMapper.class);
    Village toModel(VillageEntity entity);
    VillageEntity toEntity(Village model);
}
