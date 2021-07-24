package com.jvimora.bia.energy.service;

import com.jvimora.bia.energy.dto.MeasureDTO;
import com.jvimora.bia.energy.dto.MeasureCreationDTO;
import com.jvimora.bia.energy.dto.MeasureFilterDTO;

import java.util.Date;
import java.util.List;

public interface MeasureService {

    MeasureDTO save(MeasureCreationDTO meterDTO);

    Double average();

    List<MeasureDTO> filter(MeasureFilterDTO measureFilterDTO);

}
