package com.jvimora.bia.energy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvimora.bia.energy.dto.MeasureCreationDTO;
import com.jvimora.bia.energy.dto.MeasureDTO;
import com.jvimora.bia.energy.dto.MeasureFilterDTO;
import com.jvimora.bia.energy.service.MeasureService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class MeasureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MeasureService service;

    @InjectMocks
    MeasureController controller;

    private MeasureCreationDTO measureCreationDTO;
    private MeasureDTO measureDTO;

    @BeforeEach
    void setUp() {
        measureCreationDTO = new MeasureCreationDTO();
        measureCreationDTO.setEnergy(1.0);
        measureCreationDTO.setDeviceSN("deviceTest");
        measureCreationDTO.setTimestamp(new Date());

        measureDTO = new MeasureDTO();
        measureDTO.setId(1);
        measureDTO.setEnergy(measureCreationDTO.getEnergy());
        measureDTO.setDeviceSN(measureCreationDTO.getDeviceSN());
        measureDTO.setTimestamp(measureCreationDTO.getTimestamp());
    }

    @Test
    public void create() throws Exception {

        when(service.save(this.measureCreationDTO)).thenReturn(this.measureDTO);

        this.mockMvc.perform(
                post("/measure")
                        .content(asJsonString(this.measureCreationDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.energy").value(this.measureDTO.getEnergy()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.deviceSN").value(this.measureDTO.getDeviceSN())
                );
    }


    @Test
    void average() throws Exception {

        when(service.average()).thenReturn(1.0);

        this.mockMvc.perform(
                get("/measure/average")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string("1.0"));
    }

    @Test
    void filter() throws Exception {
        MeasureFilterDTO measureFilterDTO = new MeasureFilterDTO();
        LocalDate localDateInit = LocalDate.of(2020, Month.JANUARY, 1);
        LocalDate localDateEnd = LocalDate.of(2022, Month.DECEMBER, 1);

        measureFilterDTO.setTimestampInit(Date.from(localDateInit.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        measureFilterDTO.setTimestampEnd(Date.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        measureFilterDTO.setDeviceSN("deviceTest");

        List<MeasureDTO> list = new ArrayList<>();
        list.add(measureDTO);

        when(service.filter(measureFilterDTO)).thenReturn(list);

        this.mockMvc.perform(
                get("/measure/filter")
                        .flashAttr("filter",measureFilterDTO)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].energy").value(this.measureDTO.getEnergy()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].deviceSN").value(this.measureDTO.getDeviceSN())
                );

    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
