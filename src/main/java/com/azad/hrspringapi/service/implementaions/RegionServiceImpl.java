package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.exception.RegionServiceException;
import com.azad.hrspringapi.io.entities.Region;
import com.azad.hrspringapi.io.repositories.RegionRepository;
import com.azad.hrspringapi.service.RegionService;
import com.azad.hrspringapi.shared.dto.RegionDto;
import com.azad.hrspringapi.ui.models.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegionServiceImpl implements RegionService {

    private ModelMapper modelMapper = new ModelMapper();

    private RegionRepository regionRepository;

    @Autowired
    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public RegionDto createRegion(RegionDto regionDto) {

        Region region = modelMapper.map(regionDto, Region.class);

        Region createdRegion = regionRepository.save(region);

        return modelMapper.map(createdRegion, RegionDto.class);
    }

    @Override
    public List<RegionDto> getRegions(int page, int limit) {
        if (page > 0)
            page--;

        Pageable pageable = PageRequest.of(page, limit, Sort.by("regionName"));
        List<Region> regions = regionRepository.findAll(pageable).getContent();

        if (regions.size() == 0)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND.getErrorMessage());

        List<RegionDto> regionDtos = new ArrayList<>();
        regions.forEach(region -> regionDtos.add(modelMapper.map(region, RegionDto.class)));

        return regionDtos;
    }

    @Override
    public RegionDto getRegionById(Long id) {
        Region region = regionRepository.findById(id).orElse(null);

        if (region == null)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        return modelMapper.map(region, RegionDto.class);
    }

    @Override
    public RegionDto updateRegion(Long id, RegionDto regionDto) {
        if (regionDto.getRegionName().equals("")) {
            throw new RegionServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());
        }

        Region region = regionRepository.findById(id).orElse(null);

        if (region == null)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        region.setRegionName(regionDto.getRegionName());

        Region updatedRegion = regionRepository.save(region);

        return modelMapper.map(updatedRegion, RegionDto.class);
    }

    @Override
    public void deleteRegion(Long id) {
        Region region = regionRepository.findById(id).orElse(null);

        if (region == null)
            throw new RegionServiceException(ErrorMessages.RESOURCE_NOT_FOUND_WITH_ID.getErrorMessage() + " ID: " + id);

        regionRepository.delete(region);
    }
}
