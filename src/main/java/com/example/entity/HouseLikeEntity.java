package com.example.entity;

import com.example.enums.HouseLikeType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "house_like", uniqueConstraints = @UniqueConstraint(columnNames = {"house_id", "profile_id"}))
public class HouseLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_id")
    private Long houseId;

    @ManyToOne
    @JoinColumn(name = "house_id", insertable = false, updatable = false)
    private HouseEntity house;

    @Column(name = "profile_id")
    private Long profileId;

    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column
    @Enumerated(EnumType.STRING)
    private HouseLikeType type;
}
