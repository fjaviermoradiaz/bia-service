package com.jvimora.bia.energy.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasureDTO {

    private Integer id;
    private Date timestamp;
    private String deviceSN;
    private Double energy;
    private Date createdAt;

}
