package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Integer> {

    @Query(value = "SELECT * FROM user_info  WHERE user_id = ?", nativeQuery = true)
    UserInfoEntity findInfoByUserId(UUID userId);
}
