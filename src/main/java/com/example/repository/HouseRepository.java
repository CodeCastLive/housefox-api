package com.example.repository;

import com.example.entity.HouseEntity;
import com.example.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HouseRepository extends CrudRepository<HouseEntity, Long>, PagingAndSortingRepository<HouseEntity, Long> {
    @Query(value = "SELECT * FROM house e ORDER BY e.like_count LIMIT :limit", nativeQuery = true)
    List<HouseEntity> findTopXByLikeCount(@Param("limit") int limit);

    @Query(value = "SELECT * FROM house e ORDER BY e.view_count LIMIT :limit", nativeQuery = true)
    List<HouseEntity> findTopXByViewCount(@Param("limit") int limit);
}