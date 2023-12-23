package com.example.reactnativeapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class TokenEntity extends BaseIdEntity {
    @Column(name = "token", unique = true)
    public String token;

    @Column(name = "token_type_id")
    public int tokenTypeId;

    @Column(name = "revoked")
    public boolean revoked;

    @Column(name = "expired")
    public boolean expired;

    @Column(name = "user_id")
    public UUID userId;

    @Column(name = "token_category_id")
    public int tokenCategoryId;
}
