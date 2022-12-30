package com.example.dto.houseLike;

import com.example.enums.HouseLikeType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseLikeUpdateDTO {
    @NotNull(message = "House id required")
    private Long houseId;

    @NotNull(message = "Like type required")
    private HouseLikeType type;
}
