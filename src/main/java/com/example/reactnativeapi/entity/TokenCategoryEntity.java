package com.example.reactnativeapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_category")
public class TokenCategoryEntity extends BaseIdEntity {
    @Column(name = "token_category_name")
    private String tokenCategoryName;
}
