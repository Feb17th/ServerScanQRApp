package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.TokenCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TokenCategoryRepository extends JpaRepository<TokenCategoryEntity, Integer> {
    @Query(value = "SELECT * FROM token_category where token_category_name = ?", nativeQuery = true)
    TokenCategoryEntity findCategoryByTokenCategoryName(String tokenCategoryName);
}
