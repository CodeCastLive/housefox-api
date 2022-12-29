package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "house_image", uniqueConstraints = @UniqueConstraint(columnNames = {"house_id", "attach_id"}))
public class HouseImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "house_id")
    private Long houseId;

    @ManyToOne
    @JoinColumn(name = "house_id", insertable = false, updatable = false)
    private HouseEntity house;

    @Column(name = "attach_id")
    private String attachId;

    @ManyToOne
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attachEntity;
}
