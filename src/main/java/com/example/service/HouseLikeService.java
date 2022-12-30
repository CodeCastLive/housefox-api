package com.example.service;

import com.example.dto.houseLike.HouseLikeCreateDTO;
import com.example.dto.houseLike.HouseLikeResponseDTO;
import com.example.dto.houseLike.HouseLikeUpdateDTO;
import com.example.entity.HouseEntity;
import com.example.entity.HouseLikeEntity;
import com.example.enums.HouseLikeType;
import com.example.enums.Language;
import com.example.exceptions.HouseLikeNotFoundException;
import com.example.exceptions.HouseNotFoundException;
import com.example.exp.AlreadyLikedException;
import com.example.repository.HouseLikeRepository;
import com.example.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class HouseLikeService {
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private HouseLikeRepository houseLikeRepository;

    @Autowired
    private ResourceBundleService resourceBundleService;

    public HouseLikeResponseDTO create(HouseLikeCreateDTO dto, Long profileId, Language language) {
        Optional<HouseEntity> houseById = houseRepository.findById(dto.getHouseId());
        if (houseById.isEmpty()) {
            throw new HouseNotFoundException(resourceBundleService.getMessage("house.not.found", language));
        }

        Optional<HouseLikeEntity> byHouseIdAndProfileId = houseLikeRepository.findByHouseIdAndProfileId(dto.getHouseId(), profileId);
        if (byHouseIdAndProfileId.isPresent()) {
            throw new AlreadyLikedException(resourceBundleService.getMessage("house.already.liked", language));
        }

        HouseLikeEntity houseLikeEntity = new HouseLikeEntity();
        houseLikeEntity.setHouseId(dto.getHouseId());
        houseLikeEntity.setProfileId(profileId);
        houseLikeEntity.setCreatedDate(LocalDateTime.now());
        houseLikeEntity.setType(dto.getType());
        houseLikeRepository.save(houseLikeEntity);

        HouseLikeResponseDTO response = new HouseLikeResponseDTO();
        response.setId(houseLikeEntity.getId());
        response.setHouseId(houseLikeEntity.getHouseId());
        response.setProfileId(houseLikeEntity.getProfileId());
        response.setCreatedDate(LocalDateTime.now());
        response.setType(houseLikeEntity.getType());

        return response;
    }

    public Boolean change(HouseLikeUpdateDTO dto, Long profileId, Language language) {
        Optional<HouseEntity> houseById = houseRepository.findById(dto.getHouseId());
        if (houseById.isEmpty()) {
            throw new HouseNotFoundException(resourceBundleService.getMessage("house.not.found", language));
        }

        Optional<HouseLikeEntity> byHouseIdAndProfileId = houseLikeRepository.findByHouseIdAndProfileId(dto.getHouseId(), profileId);
        if (byHouseIdAndProfileId.isEmpty()) {
            throw new AlreadyLikedException(resourceBundleService.getMessage("house.already.liked", language));
        }

        HouseLikeEntity houseLikeEntity = byHouseIdAndProfileId.get();

        if (!houseLikeEntity.getProfileId().equals(profileId)) {

        }

        houseLikeEntity.setType(dto.getType());
        houseLikeRepository.save(houseLikeEntity);
        return true;
    }

    public Boolean deleteById(Long likeId, Long profileId, Language language) {
        Optional<HouseLikeEntity> byId = houseLikeRepository.findById(likeId);
        if (byId.isEmpty()) {
            throw new HouseLikeNotFoundException(resourceBundleService.getMessage("house.like.not.found", language));
        }

        HouseLikeEntity houseLikeEntity = byId.get();

        if (!houseLikeEntity.getProfileId().equals(profileId)) {
            return false;
        }

        houseLikeRepository.deleteById(likeId);
        return true;
    }
}
