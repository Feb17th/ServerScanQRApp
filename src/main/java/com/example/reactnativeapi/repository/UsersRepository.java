package com.example.reactnativeapi.repository;

import com.example.reactnativeapi.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {

    Optional<UsersEntity> findByUsernameAndIsDeletedFalse(String username);

    @Query(value = "SELECT * FROM users  WHERE user_name = ? and is_deleted = false", nativeQuery = true)
    UsersEntity findByUsername(String username);

    @Query(value = "SELECT * FROM users  WHERE id = ? and is_deleted = false", nativeQuery = true)
    UsersEntity findById(UUID id);
}
