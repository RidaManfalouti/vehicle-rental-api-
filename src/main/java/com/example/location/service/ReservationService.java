package com.example.location.service;

import com.example.location.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations() throws Exception;
    Reservation getReservationById(Long id);
    Reservation createReservation(Long clientId, Long vehiculeId, LocalDate dateDebut, LocalDate dateFin);
    void deleteReservation(Long id);
    double calculerMontantTotal(Reservation reservation);
    void annulerReservation(Long id);


}
