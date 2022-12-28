package com.example.entity;

import com.example.enums.Currency;
import com.example.enums.HouseType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "house")
public class HouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private String location;

    @Column
    private HouseType type;

    @Column
    private Long price;

    @Column
    private Currency currency;

    @Column
    private Integer rooms;

    @Column
    private String phone;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column
    private Long likeCount = Long.valueOf(0);

    @Column
    private Long viewCount = Long.valueOf(0);
}
