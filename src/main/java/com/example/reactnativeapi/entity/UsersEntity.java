package com.example.reactnativeapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UsersEntity extends BaseUUIdEntity {
    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;

    @Column(name = "date_deleted")
    private Date dateDeleted;
}
