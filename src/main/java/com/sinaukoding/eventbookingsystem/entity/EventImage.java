package com.sinaukoding.eventbookingsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="m_event_image", indexes = {
        @Index(name = "idx_event_image_created_date", columnList = "createdDate"),
        @Index(name = "idx_event_image_event_id", columnList = "event_id"),
        @Index(name = "idx_event_image_path", columnList = "path")
})
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data
public class EventImage extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(nullable = false)
    private String path;
}
