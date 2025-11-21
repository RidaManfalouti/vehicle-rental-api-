package com.example.location.service;

import com.example.location.dto.ReservationStatusDTO;
import com.example.location.entity.Reservation;
import com.example.location.entity.StatusReservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {
    List<Reservation> getAllReservations() throws Exception;
    Reservation getReservationById(Long id);
    public List<ReservationStatusDTO> getAllReservationByStatus(StatusReservation status);
    Reservation createReservation(Long clientId, Long vehiculeId, LocalDate dateDebut, LocalDate dateFin);
    void deleteReservation(Long id);
    double calculerMontantTotal(Reservation reservation);
    void annulerReservation(Long id);


}
