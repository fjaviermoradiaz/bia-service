package com.jvimora.bia.energy.controller;

import com.jvimora.bia.energy.dto.MeasureCreationDTO;
import com.jvimora.bia.energy.dto.MeasureDTO;
import com.jvimora.bia.energy.dto.MeasureFilterDTO;
import com.jvimora.bia.energy.service.MeasureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value ="measure")
public class MeasureController {

    private MeasureService service;

    @Autowired
    public MeasureController(MeasureService service) {
        this.service = service;
    }

    @Operation(summary = "Creates a new resource")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Resource created successfully", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MeasureDTO.class)
            )),
            @ApiResponse(responseCode = "204", description = "Request body is empty. ", content = @Content),
    })
    @PostMapping
    public ResponseEntity<MeasureDTO> create(@RequestBody MeasureCreationDTO o) {
        return ResponseEntity
                .status(CREATED)
                .body(service.save(o));
    }

    @Operation(summary = "returns averages of meters measures")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = Double.class)
            ))
    })
    @GetMapping("average")
    public ResponseEntity<Double> average() {
        return ResponseEntity
                .status(OK)
                .body(service.average());
    }


    @Operation(summary = "returns a sorted page of resources that meet the filter conditions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = List.class)
            ))
    })
    @GetMapping("filter")
    public ResponseEntity<List<MeasureDTO>> filter(@ModelAttribute(value = "filter") MeasureFilterDTO filter) {
        return ResponseEntity.ok(service.filter(filter));
    }

}
