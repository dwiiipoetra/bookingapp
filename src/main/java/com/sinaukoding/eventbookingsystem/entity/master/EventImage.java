package com.sinaukoding.eventbookingsystem.entity.master;

import com.sinaukoding.eventbookingsystem.entity.app.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Entity
@Table(name="m_event_image", indexes = {
        @Index(name = "idx_event_image_created_date", columnList = "createdDate"),
        @Index(name = "idx_event_image_event_id", columnList = "event_id"),
        @Index(name = "idx_event_image_path", columnList = "path")
})
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
//@Data
public class EventImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    @ToString.Exclude
    private Event event;

    @Column(nullable = false)
    private String path;
}
