package com.jvimora.bia.energy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasureFilterDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestampInit;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestampEnd;
    private String deviceSN;

}
