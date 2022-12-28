package com.example.controller;

import com.example.config.security.CustomUserDetails;
import com.example.dto.house.HouseCreateDTO;
import com.example.dto.house.HouseFullResponseDTO;
import com.example.dto.house.HouseResponseDTO;
import com.example.dto.house.HouseUpdateDTO;
import com.example.enums.Language;
import com.example.service.HouseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/house")
@Tag(name = "House Controller", description = "This url is used to manage homes")
public class HouseController {
    @Autowired
    private HouseService houseService;

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create house method", description = "This method is used to create house")
    @PostMapping("/create")
    public ResponseEntity<HouseResponseDTO> create(@Valid @RequestBody HouseCreateDTO dto) {
        System.out.println("Hello world");
        HouseResponseDTO result = houseService.create(dto, getUserId());
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update house method", description = "This method is used to update house")
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Long id, @Valid @RequestBody HouseUpdateDTO dto,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        Boolean result = houseService.update(id, dto, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete house method", description = "This method is used to delete house")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        Boolean result = houseService.delete(id, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get house by id", description = "This method is used to get house by id")
    @GetMapping("/get/{id}")
    public ResponseEntity<HouseFullResponseDTO> getById(@PathVariable Long id,
                                                        @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        HouseFullResponseDTO result = houseService.getById(id, language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @Operation(summary = "Get all houses pagination", description = "This method is used to get all houses with pagination")
    @GetMapping("/get/all")
    public ResponseEntity<Page<HouseFullResponseDTO>> getAll(@RequestParam("page") Integer page,
                                                             @RequestParam("size") Integer size,
                                                             @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        Page<HouseFullResponseDTO> result = houseService.getAll(page, size, language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get top 10 liked houses", description = "This method is used to get top 10 liked houses")
    @GetMapping("/get/top/liked")
    public ResponseEntity<List<HouseFullResponseDTO>> getTop10Liked(@RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        List<HouseFullResponseDTO> result = houseService.getLikedTop10(language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get top 10 viewed houses", description = "This method is used to get top 10 viewed houses")
    @GetMapping("/get/top/viewed")
    public ResponseEntity<List<HouseFullResponseDTO>> getTop10(@RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        List<HouseFullResponseDTO> result = houseService.getViewedTop10(language);
        return ResponseEntity.ok(result);
    }

    private Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}
