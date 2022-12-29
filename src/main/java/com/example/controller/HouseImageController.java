package com.example.controller;

import com.example.dto.house.image.HouseImageCreateDTO;
import com.example.dto.house.image.HouseImageResponseDTO;
import com.example.dto.house.image.HouseImageUpdateDTO;
import com.example.enums.Language;
import com.example.service.HouseImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house/image")
@Tag(name = "House Image Controller", description = "This url is used to manage images in home")
public class HouseImageController {
    @Autowired
    private HouseImageService houseImageService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create image to house method", description = "This method is used to create image for house")
    @PostMapping("/create")
    public ResponseEntity<HouseImageResponseDTO> create(@Valid @RequestBody HouseImageCreateDTO dto) {
        HouseImageResponseDTO result = houseImageService.create(dto);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update image in house method", description = "This method is used to update image in house")
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable Long id, @Valid @RequestBody HouseImageUpdateDTO dto,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        Boolean result = houseImageService.update(id, dto, language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete image in house method", description = "This method is used to delete image in house")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id,
                                              @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        Boolean result = houseImageService.delete(id, language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get image by id", description = "This method is used to get image by id")
    @GetMapping("/get/{id}")
    public ResponseEntity<HouseImageResponseDTO> getById(@PathVariable Long id,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        HouseImageResponseDTO result = houseImageService.getById(id, language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get image by id", description = "This method is used to get image by id")
    @GetMapping("/get/house/{id}")
    public ResponseEntity<List<HouseImageResponseDTO>> getHouseImages(@PathVariable Long id,
                                                         @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        List<HouseImageResponseDTO> result = houseImageService.getHouseImages(id, language);
        return ResponseEntity.ok(result);
    }
}
