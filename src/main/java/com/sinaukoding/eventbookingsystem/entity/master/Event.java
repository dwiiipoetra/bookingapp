package com.sinaukoding.eventbookingsystem.entity.master;

import com.sinaukoding.eventbookingsystem.entity.app.BaseEntity;
import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "m_event", indexes = {
        @Index(name = "idx_event_created_date", columnList = "createdDate"),
        @Index(name = "idx_event_title", columnList = "title"),
        @Index(name = "idx_event_description", columnList = "description"),
        @Index(name = "idx_event_location", columnList = "location"),
})
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(max = 100, message = "Max character is 100")
    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Size(max = 50, message = "Max character is 50")
    @Column(nullable = false)
    private String location;

    @NotNull(message = "Start time cannot be null")
    @Column(nullable = false)
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    @Column(nullable = false)
    private LocalDateTime endTime;

    @Min(value = 0, message = "Capacity cannot be negative")
    @Column(nullable = false)
    private Integer capacity;

    @Min(value = 0, message = "Price cannot be negative")
    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private Set<EventImage> eventsImage = new HashSet<>();
}