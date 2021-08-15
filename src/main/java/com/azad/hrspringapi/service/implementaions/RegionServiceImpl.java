package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.exception.RegionServiceException;
import com.azad.hrspringapi.io.entities.Region;
import com.azad.hrspringapi.io.repositories.RegionRepository;
import com.azad.hrspringapi.service.RegionService;
import com.azad.hrspringapi.ui.models.response.ErrorMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public Region createRegion(Region region) {

        return regionRepository.save(region);
    }

    @Override
    public List<Region> getRegions(int page, int limit) {
        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("regionName"));
        List<Region> regions = regionRepository.findAll(pageable).getContent();

        if (regions.size() == 0)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());

        return regions;
    }

    @Override
    public Region getRegionById(Long id) {
        Region region = regionRepository.findById(id).orElse(null);

        if (region == null)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        return region;
    }

    @Override
    public Region updateRegion(Long id, Region updatedRegionData) {
        if (updatedRegionData.getRegionName().equals("")) {
            throw new RegionServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        Region region = regionRepository.findById(id).orElse(null);

        if (region == null)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        region.setRegionName(updatedRegionData.getRegionName());

        return regionRepository.save(region);
    }

    @Override
    public void deleteRegion(Long id) {
        Region region = regionRepository.findById(id).orElse(null);

        if (region == null)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        regionRepository.delete(region);
    }
}
