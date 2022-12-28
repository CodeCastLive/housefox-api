package com.example.dto.house;

import com.example.enums.Currency;
import com.example.enums.HouseType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HouseFullResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String location;
    private HouseType type;
    private Long price;
    private Currency currency;
    private Integer rooms;
    private String phone;
    private LocalDateTime createdDate;
    private Long likeCount;
    private Long viewCount;
}
