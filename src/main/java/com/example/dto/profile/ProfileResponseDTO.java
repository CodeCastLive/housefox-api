package com.example.dto.profile;

import com.example.enums.ProfileStatus;
import com.example.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponseDTO {

    private Long id;
    private String name;
    private String surname;

    private String email;
    private ProfileStatus status;
    private ProfileRole role;
    private Boolean visible;

    private String photoId;
    private LocalDateTime createdDate;

    public ProfileResponseDTO() {
    }


    public ProfileResponseDTO(Long id, String name, String surname, ProfileStatus status, ProfileRole role, Boolean visible, LocalDateTime createdDate) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.status = status;
        this.role = role;
        this.visible = visible;
        this.createdDate = createdDate;
    }
}
