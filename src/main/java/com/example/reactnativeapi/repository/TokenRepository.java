package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<TokenEntity, Integer> {
    List<TokenEntity> findAllByUserIdAndExpiredFalseAndRevokedFalse(UUID userId);

    List<TokenEntity> findAllByUserIdAndTokenCategoryIdAndExpiredFalseAndRevokedFalse(UUID userId, Integer tokenCategoryId);

    Optional<TokenEntity> findByToken(String token);

    Optional<TokenEntity> findByTokenAndAndTokenCategoryIdAndExpiredFalseAndRevokedFalse(String token, Integer id);
}
