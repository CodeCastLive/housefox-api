package com.example.controller;

import com.example.config.security.CustomUserDetails;
import com.example.dto.houseLike.HouseLikeCreateDTO;
import com.example.dto.houseLike.HouseLikeResponseDTO;
import com.example.dto.houseLike.HouseLikeUpdateDTO;
import com.example.enums.Language;
import com.example.service.HouseLikeService;
import com.fasterxml.jackson.databind.ser.std.StdArraySerializers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/house/like")
@Tag(name = "House like Controller", description = "This url is used to manage images in home")
public class HouseLikeController {
    @Autowired
    private HouseLikeService houseLikeService;

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Create like to house method", description = "This method is used to create like for house")
    @PostMapping("/create")
    public ResponseEntity<HouseLikeResponseDTO> create(@Valid @RequestBody HouseLikeCreateDTO dto,
                                                       @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        HouseLikeResponseDTO result = houseLikeService.create(dto, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Change like type method", description = "This method is used to change like type")
    @PutMapping("/change")
    public ResponseEntity<Boolean> change(@Valid @RequestBody HouseLikeUpdateDTO dto,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        Boolean result = houseLikeService.change(dto, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Delete like method", description = "This method is used to delete like")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        Boolean result = houseLikeService.deleteById(id, getUserId(), language);
        return ResponseEntity.ok(result);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        return user.getId();
    }
}
