package com.example.dto.house;

import com.example.enums.HouseType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseUpdateDTO {
    @NotBlank(message = "Title required")
    private String title;

    @NotNull(message = "Description required")
    private String description;

    @NotBlank(message = "Location required")
    private String location;

    @NotNull(message = "Type required")
    private HouseType type;

    @Min(value = 0, message = "Price Required")
    private Long price;

    @Min(value = 1, message = "Rooms required")
    private Integer rooms;

    @NotBlank(message = "Phone required")
    private String phone;
}
