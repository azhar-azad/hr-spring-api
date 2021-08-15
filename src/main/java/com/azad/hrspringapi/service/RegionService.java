package com.azad.hrspringapi.service;

import com.azad.hrspringapi.io.entities.Region;

import java.util.List;

public interface RegionService {

    Region createRegion(Region region);

    Region getRegionById(Long id);

    List<Region> getRegions(int page, int limit);

    Region updateRegion(Long id, Region region);

    void deleteRegion(Long id);
}
