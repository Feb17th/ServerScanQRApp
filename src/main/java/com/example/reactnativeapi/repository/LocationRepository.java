package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {

    @Query(value = "SELECT * FROM location  WHERE id = ?1", nativeQuery = true)
    LocationEntity findOneById(UUID id);

    @Query(value = "SELECT * FROM location", nativeQuery = true)
    List<LocationEntity> findAllLocation();
}
