package com.jvimora.bia.energy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasureCreationDTO {

    private Date timestamp;
    private String deviceSN;
    private Double energy;

}
