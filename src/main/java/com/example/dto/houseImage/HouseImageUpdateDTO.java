package com.example.dto.houseImage;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseImageUpdateDTO {
    @NotNull(message = "House id required")
    private Long houseId;

    @NotNull(message = "Attach id required")
    private String attachId;
}
