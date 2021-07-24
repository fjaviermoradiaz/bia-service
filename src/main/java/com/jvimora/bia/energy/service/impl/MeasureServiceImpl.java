package com.jvimora.bia.energy.service.impl;

import com.jvimora.bia.energy.dto.MeasureCreationDTO;
import com.jvimora.bia.energy.dto.MeasureDTO;
import com.jvimora.bia.energy.dto.MeasureFilterDTO;
import com.jvimora.bia.energy.mapper.MeasureMapper;
import com.jvimora.bia.energy.model.Measure;
import com.jvimora.bia.energy.repository.MeasureRepository;
import com.jvimora.bia.energy.service.MeasureService;
import com.jvimora.bia.energy.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MeasureServiceImpl implements MeasureService {

    private MeasureRepository repository;
    private MeasureMapper mapper;

    @Autowired
    public MeasureServiceImpl(MeasureRepository repository, MeasureMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MeasureDTO save(MeasureCreationDTO meterDTO) {
        return mapper.toDto(repository.save(mapper.toEntity(meterDTO)));
    }

    @Override
    public Double average() {
        List<Measure> listDeviceMeasure = repository.findAll();

        return MathUtils.parseDouble(listDeviceMeasure.stream()
                .mapToDouble(measure -> measure.getEnergy())
                .average()
                .orElse(0.0));

    }

    @Override
    public List<MeasureDTO> filter(MeasureFilterDTO measureFilterDTO) {
        return repository.findByTimestampBetweenAndDeviceSN(measureFilterDTO.getTimestampInit(),
                    measureFilterDTO.getTimestampEnd(),
                    measureFilterDTO.getDeviceSN())
                .stream()
                .map(mapper::toDto)
                .collect(toList());

    }

}
