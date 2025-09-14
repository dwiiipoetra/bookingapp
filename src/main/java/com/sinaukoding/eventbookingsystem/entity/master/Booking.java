package com.sinaukoding.eventbookingsystem.entity.master;

import com.sinaukoding.eventbookingsystem.entity.app.BaseEntity;
import com.sinaukoding.eventbookingsystem.entity.management_user.User;
import com.sinaukoding.eventbookingsystem.model.enums.BookingStatus;
import com.sinaukoding.eventbookingsystem.model.enums.PaymentStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_booking", indexes = {
        @Index(name = "idx_booking_created_date", columnList = "createdDate"),
        @Index(name = "idx_booking_date", columnList = "bookingDate"),
        @Index(name = "idx_booking_status", columnList = "bookingStatus"),
        @Index(name = "idx_payment_status", columnList = "paymentStatus"),
})
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id;

    @NotNull(message = "Booking Date cannot be null")
    @Column(nullable = false)
    private LocalDate bookingDate;

    @NotNull(message = "Booking Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @NotNull(message = "Payment Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    @ToString.Exclude
    private Event event;
}