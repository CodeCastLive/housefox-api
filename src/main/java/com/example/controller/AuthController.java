package com.example.controller;


import com.example.dto.auth.LoginDTO;
import com.example.dto.auth.LoginResponseDTO;
import com.example.dto.auth.RegistrationDTO;
import com.example.dto.profile.ProfileResponseDTO;
import com.example.enums.Language;
import com.example.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@Tag(name = "Authorization Controller", description = "This controller for authorization")
public class AuthController {
    @Autowired
    private AuthService service;

    @Operation(summary = "Method for registration", description = "This method used to create a user")
    @PostMapping("/registration")
    private ResponseEntity<ProfileResponseDTO> registration(@Valid @RequestBody RegistrationDTO dto, @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {
        log.info("Registration : email {}, name {}", dto.getEmail(), dto.getName());

        ProfileResponseDTO result = service.registration(dto, language);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Method fro verification", description = "This method used to verifying by email after registration")
    @GetMapping("/verification/email/{jwt}")
    private ResponseEntity<String> verification(@PathVariable("jwt") String jwt) {
        log.info("Verification: jwt {}", jwt);
        String result = service.verification(jwt);
        return ResponseEntity.ok(result);
    }


    @Operation(summary = "Method for authorization", description = "This method used for Login")
    @PostMapping("/authorization")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO dto,
                                                  @RequestHeader(value = "Accept-Language", defaultValue = "RU") Language language) {


        log.info(" Login :  email {} ", dto.getEmail());
        LoginResponseDTO response = service.login(dto, language);
        return ResponseEntity.ok(response);
    }

}
