package com.example.service;


import com.example.dto.JwtDTO;
import com.example.dto.auth.LoginDTO;
import com.example.dto.auth.LoginResponseDTO;
import com.example.dto.auth.RegistrationDTO;
import com.example.dto.profile.ProfileResponseDTO;
import com.example.entity.ProfileEntity;
import com.example.enums.Language;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.repository.ProfileRepository;
import com.example.util.JwtUtil;
import com.example.util.MD5;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class AuthService {
    private final ProfileRepository repository;

    private final ResourceBundleService resourceBundleService;

    public AuthService(ProfileRepository repository, ResourceBundleService resourceBundleService) {
        this.repository = repository;
        this.resourceBundleService = resourceBundleService;
    }


    public ProfileResponseDTO registration(RegistrationDTO dto, Language language) {
        Optional<ProfileEntity> exists = repository.findByEmail(dto.getEmail());
        if (exists.isPresent()) {
            ProfileEntity entity = exists.get();
            if (entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
                repository.delete(entity);
            } else {

            }
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5.md5(dto.getPassword()));


        entity.setVisible(true);
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());
        entity.setRole(ProfileRole.ROLE_USER);

        repository.save(entity);
        return getDTO(entity);

    }


    public ProfileResponseDTO getDTO(ProfileEntity entity) {

        ProfileResponseDTO profileDTO = new ProfileResponseDTO();
        profileDTO.setId(entity.getId());
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setEmail(entity.getEmail());
        profileDTO.setStatus(entity.getStatus());
        profileDTO.setRole(entity.getRole());
        profileDTO.setVisible(entity.getVisible());
        profileDTO.setCreatedDate(entity.getCreatedDate());

        return profileDTO;
    }


    public ProfileEntity getEntity(RegistrationDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPassword(MD5.md5(dto.getPassword()));

        return entity;
    }

    public LoginResponseDTO login(LoginDTO dto, Language language) {
        Optional<ProfileEntity> optional = repository.findByEmailAndPassword(dto.getEmail(), MD5.md5(dto.getPassword()));
        if (optional.isEmpty()) {
            System.out.println("Hello world");
            return null;
        }

        ProfileEntity entity = optional.get();
        if (entity.getStatus().equals(ProfileStatus.BLOCK)) {
            System.out.println("Hello mother***");
            return null;
        }

        // TODO
//        if (entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)){
//            throw new ProfileNotActiveException("Profile Is Not Active");
//        }

        LoginResponseDTO responseDTO = new LoginResponseDTO();
        responseDTO.setName(entity.getName());
        responseDTO.setSurname(entity.getSurname());
        responseDTO.setRole(entity.getRole());
        responseDTO.setToken(JwtUtil.encode(entity.getEmail(), entity.getRole()));

        return responseDTO;
    }

    public String verification(String jwt) {


        JwtDTO jwtDTO;
        try {
            jwtDTO = JwtUtil.decodeToken(jwt);
        } catch (JwtException e) {
            return "Verification failed";
        }

        Optional<ProfileEntity> optional = repository.findByEmail(jwtDTO.getEmail());

        if (optional.isEmpty()) {
            return "Verification failed";
        }

        ProfileEntity entity = optional.get();

        if (!entity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
            return "Verification failed";
        }
        entity.setStatus(ProfileStatus.ACTIVE);

        repository.save(entity);
        return "verification success";
    }
}
