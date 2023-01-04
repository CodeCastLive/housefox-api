package com.example.dto.house;

import com.example.enums.Currency;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class HouseShortResponseDTO {
    private Long id;
    private String title;
    private Long price;
    private Currency currency;
    private String imageUrl;
}
