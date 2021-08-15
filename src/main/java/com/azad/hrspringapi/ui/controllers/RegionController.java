package com.azad.hrspringapi.ui.controllers;

import com.azad.hrspringapi.io.entities.Region;
import com.azad.hrspringapi.service.RegionService;
import com.azad.hrspringapi.ui.models.request.RegionDetailRequestModel;
import com.azad.hrspringapi.ui.models.response.OperationStatusModel;
import com.azad.hrspringapi.ui.models.response.RegionRestResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("regions")
public class RegionController {

    private ModelMapper modelMapper = new ModelMapper();

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
    public ResponseEntity<RegionRestResponse> createRegion(@RequestBody RegionDetailRequestModel regionDetails) {
        Region region = modelMapper.map(regionDetails, Region.class);

        Region createdRegion = regionService.createRegion(region);

        RegionRestResponse responseBody = modelMapper.map(createdRegion, RegionRestResponse.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @GetMapping
    public ResponseEntity<List<RegionRestResponse>> getRegions(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<Region> regions = regionService.getRegions(page, limit);

        List<RegionRestResponse> regionResponseList = new ArrayList<>();
        regions.forEach(region -> regionResponseList.add(modelMapper.map(region, RegionRestResponse.class)));

        return ResponseEntity.status(HttpStatus.OK).body(regionResponseList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RegionRestResponse> getRegion(@PathVariable Long id) {
        Region region = regionService.getRegionById(id);

        RegionRestResponse responseBody = modelMapper.map(region, RegionRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RegionRestResponse> updateRegion(@PathVariable Long id, @RequestBody RegionDetailRequestModel regionDetails) {
        Region region = modelMapper.map(regionDetails, Region.class);

        Region updatedRegion = regionService.updateRegion(id, region);

        RegionRestResponse responseBody = modelMapper.map(updatedRegion, RegionRestResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<OperationStatusModel> deleteRegion(@PathVariable Long id) {
        OperationStatusModel responseBody = new OperationStatusModel();
        responseBody.setOperationName("DELETE");
        regionService.deleteRegion(id);
        responseBody.setOperationResult("SUCCESS");

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
