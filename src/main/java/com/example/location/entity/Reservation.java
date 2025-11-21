package com.example.location.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private double montantTotal;

    @ManyToOne
    private Vehicule vehicule;

    @Enumerated(EnumType.STRING)
    private StatusReservation statusReservation;

    @ManyToOne
    private Client client;

}
