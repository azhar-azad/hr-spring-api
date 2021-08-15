package com.azad.hrspringapi.service;

import com.azad.hrspringapi.shared.dto.RegionDto;

import java.util.List;

public interface RegionService {

    RegionDto createRegion(RegionDto regionDto);

    RegionDto getRegionById(Long id);

    List<RegionDto> getRegions(int page, int limit);

    RegionDto updateRegion(Long id, RegionDto regionDto);

    void deleteRegion(Long id);
}
