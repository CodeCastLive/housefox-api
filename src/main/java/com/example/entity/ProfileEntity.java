package com.example.entity;

import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column
    private ProfileStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column
    private ProfileRole role;

    @Column
    private Boolean visible = true;


    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();

}
