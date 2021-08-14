package com.azad.hrspringapi.ui.controllers;

import com.azad.hrspringapi.service.RegionService;
import com.azad.hrspringapi.shared.dto.RegionDto;
import com.azad.hrspringapi.ui.models.request.RegionDetailRequestModel;
import com.azad.hrspringapi.ui.models.response.RegionRestResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("regions")
public class RegionController {

    ModelMapper modelMapper = new ModelMapper();

    private RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping(path = "/test")
    public String regionRouterTest() {
        return "Region controller is up and running";
    }

    @PostMapping
    public RegionRestResponse createRegion(@RequestBody RegionDetailRequestModel regionDetails) {
        RegionDto regionDto = modelMapper.map(regionDetails, RegionDto.class);

        RegionDto createdRegion = regionService.createRegion(regionDto);

        return modelMapper.map(createdRegion, RegionRestResponse.class);
    }
}
