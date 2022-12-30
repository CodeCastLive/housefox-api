package com.example.dto.houseLike;

import com.example.enums.HouseLikeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HouseLikeResponseDTO {
    private Long id;
    private Long houseId;
    private Long profileId;
    private LocalDateTime createdDate;
    private HouseLikeType type;
}
