package com.example.reactnativeapi.repository;



import com.example.reactnativeapi.entity.TokenTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TokenTypeRepository extends JpaRepository<TokenTypeEntity, Integer> {
    @Query(value = "SELECT * FROM token_type where token_type_name = ?", nativeQuery = true)
    TokenTypeEntity findTypeByTokenTypeName(String tokenTypeName);
}
