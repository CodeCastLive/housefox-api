package com.example.repository;

import com.example.entity.HouseImageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseImageRepository extends CrudRepository<HouseImageEntity, Long>, PagingAndSortingRepository<HouseImageEntity, Long> {
    List<HouseImageEntity> findByHouseId(Long houseId);
}