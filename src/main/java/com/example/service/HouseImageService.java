package com.example.service;

import com.example.dto.house.image.HouseImageCreateDTO;
import com.example.dto.house.image.HouseImageResponseDTO;
import com.example.dto.house.image.HouseImageUpdateDTO;
import com.example.entity.HouseImageEntity;
import com.example.enums.Language;
import com.example.exceptions.HouseImageNotFoundException;
import com.example.repository.HouseImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseImageService {
    @Autowired
    private HouseImageRepository houseImageRepository;

    @Autowired
    private ResourceBundleService resourceBundleService;

    public HouseImageResponseDTO create(HouseImageCreateDTO dto) {
        HouseImageEntity entity = new HouseImageEntity();
        entity.setHouseId(dto.getHouseId());
        entity.setAttachId(dto.getAttachId());
        houseImageRepository.save(entity);

        HouseImageResponseDTO response = new HouseImageResponseDTO();
        response.setId(entity.getId());
        response.setHouseId(entity.getHouseId());
        response.setAttachId(entity.getAttachId());
        return response;
    }

    public Boolean update(Long id, HouseImageUpdateDTO dto, Language language) {
        Optional<HouseImageEntity> byId = houseImageRepository.findById(id);
        if (byId.isEmpty()) {
            throw new HouseImageNotFoundException(resourceBundleService.getMessage("house.image.not.found", language));
        }

        HouseImageEntity entity = byId.get();
        entity.setHouseId(dto.getHouseId());
        entity.setAttachId(dto.getAttachId());
        houseImageRepository.save(entity);

        return true;
    }


    public Boolean delete(Long id, Language language) {
        Optional<HouseImageEntity> byId = houseImageRepository.findById(id);
        if (byId.isEmpty()) {
            throw new HouseImageNotFoundException(resourceBundleService.getMessage("house.image.not.found", language));
        }

        HouseImageEntity entity = byId.get();
        houseImageRepository.delete(entity);

        return true;
    }

    public HouseImageResponseDTO getById(Long id, Language language) {
        Optional<HouseImageEntity> byId = houseImageRepository.findById(id);
        if (byId.isEmpty()) {
            throw new HouseImageNotFoundException(resourceBundleService.getMessage("house.image.not.found", language));
        }

        HouseImageEntity entity = byId.get();

        return toHouseImageResponse(entity);
    }

    public List<HouseImageResponseDTO> getHouseImages(Long id, Language language) {
        List<HouseImageEntity> byHouseId = houseImageRepository.findByHouseId(id);
        List<HouseImageResponseDTO> result = new LinkedList<>();
        for (HouseImageEntity entity : byHouseId) {
            result.add(toHouseImageResponse(entity));
        }

        return result;
    }

    private HouseImageResponseDTO toHouseImageResponse(HouseImageEntity entity) {
        HouseImageResponseDTO response = new HouseImageResponseDTO();
        response.setId(entity.getId());
        response.setHouseId(entity.getHouseId());
        response.setAttachId(entity.getAttachId());
        return response;
    }
}
