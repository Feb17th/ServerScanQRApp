package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    @Query(value = "SELECT * FROM user_role WHERE user_id = ?1", nativeQuery = true)
    List<UserRoleEntity> findByUserId(UUID id);
}
