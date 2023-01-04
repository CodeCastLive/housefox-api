package com.example.service;

import com.example.dto.house.*;
import com.example.dto.houseImage.HouseImageResponseDTO;
import com.example.entity.HouseEntity;
import com.example.enums.Currency;
import com.example.enums.Language;
import com.example.exceptions.HouseNotFoundException;
import com.example.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class HouseService {
    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private ResourceBundleService resourceBundleService;

    @Autowired
    private HouseImageService houseImageService;

    @Autowired
    private AttachService attachService;

    @Value("${attach.upload.folder}")
    private String attachUploadFolder;

    public HouseResponseDTO create(HouseCreateDTO dto) {
        HouseEntity houseEntity = new HouseEntity();
        houseEntity.setTitle(dto.getTitle());
        houseEntity.setDescription(dto.getDescription());
        houseEntity.setLocation(dto.getLocation());
        houseEntity.setType(dto.getType());
        houseEntity.setPrice(dto.getPrice());
        houseEntity.setCurrency(Currency.SUM);
        houseEntity.setRooms(dto.getRooms());
        houseEntity.setPhone(dto.getPhone());
        houseEntity.setCreatedDate(LocalDateTime.now());

        houseRepository.save(houseEntity);
        HouseResponseDTO response = new HouseResponseDTO();
        response.setId(houseEntity.getId());
        response.setTitle(houseEntity.getTitle());
        response.setDescription(houseEntity.getDescription());
        response.setLocation(houseEntity.getLocation());
        response.setType(houseEntity.getType());
        response.setPrice(houseEntity.getPrice());
        response.setCurrency(houseEntity.getCurrency());
        response.setRooms(houseEntity.getRooms());
        response.setPhone(houseEntity.getPhone());
        response.setCreatedDate(houseEntity.getCreatedDate());

        return response;
    }


    public Boolean update(Long id, HouseUpdateDTO dto, Language language) {
        Optional<HouseEntity> byId = houseRepository.findById(id);
        if (byId.isEmpty()) {
            throw new HouseNotFoundException(resourceBundleService.getMessage("house.not.found", language));
        }

        HouseEntity houseEntity = byId.get();
        houseEntity.setTitle(dto.getTitle());
        houseEntity.setDescription(dto.getDescription());
        houseEntity.setLocation(dto.getLocation());
        houseEntity.setType(dto.getType());
        houseEntity.setPrice(dto.getPrice());
        houseEntity.setRooms(dto.getRooms());
        houseEntity.setPhone(dto.getPhone());
        houseEntity.setCreatedDate(LocalDateTime.now());
        houseRepository.save(houseEntity);
        return true;
    }

    public Boolean delete(Long id, Language language) {
        Optional<HouseEntity> byId = houseRepository.findById(id);
        if (byId.isEmpty()) {
            throw new HouseNotFoundException(resourceBundleService.getMessage("house.not.found", language));
        }

        HouseEntity houseEntity = byId.get();
        houseRepository.delete(houseEntity);
        return true;
    }

    public HouseFullResponseDTO getById(Long id, Language language) {
        Optional<HouseEntity> byId = houseRepository.findById(id);
        if (byId.isEmpty()) {
            throw new HouseNotFoundException(resourceBundleService.getMessage("house.not.found", language));
        }

        HouseEntity houseEntity = byId.get();
        return toHouseFullResponse(houseEntity);
    }

    public Page<HouseFullResponseDTO> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<HouseEntity> pageObj = houseRepository.findAll(pageable);

        List<HouseEntity> content = pageObj.getContent();

        List<HouseFullResponseDTO> result = new LinkedList<>();
        for (HouseEntity houseEntity : content) {
            result.add(toHouseFullResponse(houseEntity));
        }

        return new PageImpl<>(result, pageable, pageObj.getTotalElements());
    }


    public Page<HouseShortResponseDTO> getAllShortInfo(Integer page, Integer size, Language language) {
        Pageable pageable = PageRequest.of(page, size);

        Page<HouseEntity> pageObj = houseRepository.findAll(pageable);

        List<HouseEntity> content = pageObj.getContent();

        List<HouseShortResponseDTO> result = new LinkedList<>();
        for (HouseEntity houseEntity : content) {
            result.add(toHouseShortResponse(houseEntity, language));
        }

        return new PageImpl<>(result, pageable, pageObj.getTotalElements());
    }

    private HouseShortResponseDTO toHouseShortResponse(HouseEntity entity, Language language) {
        HouseShortResponseDTO response = new HouseShortResponseDTO();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setPrice(entity.getPrice());
        response.setCurrency(entity.getCurrency());
        response.setImageUrl(getImageUrl(entity, language));

        return response;
    }

    private String getImageUrl(HouseEntity entity, Language language) {
        // In here only one image going first image...
        List<HouseImageResponseDTO> houseImages = houseImageService.getHouseImages(entity.getId(), language);
        if (houseImages.size() == 0) {
            return null;
        }

        HouseImageResponseDTO image = houseImages.get(0);
        return attachService.getUrl(image.getAttachId(), language);
    }

    private HouseFullResponseDTO toHouseFullResponse(HouseEntity houseEntity) {
        HouseFullResponseDTO response = new HouseFullResponseDTO();
        response.setId(houseEntity.getId());
        response.setTitle(houseEntity.getTitle());
        response.setDescription(houseEntity.getDescription());
        response.setLocation(houseEntity.getLocation());
        response.setType(houseEntity.getType());
        response.setPrice(houseEntity.getPrice());
        response.setCurrency(houseEntity.getCurrency());
        response.setRooms(houseEntity.getRooms());
        response.setPhone(houseEntity.getPhone());
        response.setCreatedDate(houseEntity.getCreatedDate());
        response.setLikeCount(houseEntity.getLikeCount());
        response.setViewCount(houseEntity.getViewCount());
        return response;
    }

    public List<HouseFullResponseDTO> getLikedTop10() {
        List<HouseEntity> top10ByLikeCount = houseRepository.findTopXByLikeCount(10);
        List<HouseFullResponseDTO> result = new LinkedList<>();

        for (HouseEntity houseEntity : top10ByLikeCount) {
            result.add(toHouseFullResponse(houseEntity));
        }

        return result;
    }


    public List<HouseFullResponseDTO> getViewedTop10() {
        List<HouseEntity> top10ByLikeCount = houseRepository.findTopXByViewCount(10);
        List<HouseFullResponseDTO> result = new LinkedList<>();

        for (HouseEntity houseEntity : top10ByLikeCount) {
            result.add(toHouseFullResponse(houseEntity));
        }

        return result;
    }
}
