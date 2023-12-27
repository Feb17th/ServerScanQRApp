package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Integer> {

    @Query(value = "SELECT * FROM history where user_id = ? and is_deleted = false", nativeQuery = true)
    List<HistoryEntity> findAllHistory(UUID userId);

    @Query(value = "SELECT * FROM history where user_id = ? and is_saving = true", nativeQuery = true)
    List<HistoryEntity> findAllStarLocation(UUID userId);

    @Query(value = "SELECT count(id) FROM history where user_id = ? and location_id = ?", nativeQuery = true)
    Integer checkExisting(UUID userId, UUID locationId);

    @Query(value = "SELECT * FROM history where id = ?", nativeQuery = true)
    HistoryEntity findOneById(int id);
}
