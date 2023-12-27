package com.example.reactnativeapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "history")
public class HistoryEntity extends BaseIdEntity{
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "location_id", nullable = false)
    private UUID locationId;
    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted;
    @Column(name = "is_saving", nullable = false)
    private Boolean isSaving;
    @Column(name = "time_scan", nullable = false)
    private LocalDateTime timeScan;
}
