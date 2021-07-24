package com.jvimora.bia.energy.mapper;

import com.jvimora.bia.energy.dto.MeasureCreationDTO;
import com.jvimora.bia.energy.dto.MeasureDTO;
import com.jvimora.bia.energy.model.Measure;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface MeasureMapper {


    MeasureDTO toDto(Measure measure);

    @InheritInverseConfiguration
    Measure toEntity(MeasureDTO o);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Measure toEntity(MeasureCreationDTO o);
}


