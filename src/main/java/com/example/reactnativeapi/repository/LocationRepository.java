package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
}
