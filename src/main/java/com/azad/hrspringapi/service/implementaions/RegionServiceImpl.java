package com.azad.hrspringapi.service.implementaions;

import com.azad.hrspringapi.io.entities.Region;
import com.azad.hrspringapi.io.repositories.RegionRepository;
import com.azad.hrspringapi.service.RegionService;
import com.azad.hrspringapi.shared.dto.RegionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {

    ModelMapper modelMapper = new ModelMapper();

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
}
