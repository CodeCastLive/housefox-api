package com.example.repository;

import com.example.entity.HouseLikeEntity;
import com.example.exceptions.HouseLikeNotFoundException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseLikeRepository extends CrudRepository<HouseLikeEntity, Long>, PagingAndSortingRepository<HouseLikeEntity, Long> {
    Optional<HouseLikeEntity> findByHouseIdAndProfileId(Long houseId, Long profileId);
    Optional<HouseLikeEntity> findById(Long id); // ?? But why I have to override
}
