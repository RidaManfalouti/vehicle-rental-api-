package com.example.location.impService;

import com.example.location.entity.Reservation;
import com.example.location.repository.ReservationRepository;
import com.example.location.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpReservation implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public List<Reservation> getAllReservations() throws Exception {
        if (reservationRepository.findAll().isEmpty()) {
            throw new Exception("pas des reservations exist");
        }
        else {
            return reservationRepository.findAll();
        }
    }

    @Override
    public Reservation getReservationById(Long id) {
        if (reservationRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Reservation with id " + id + " not found");
        }
        return reservationRepository.findById(id).get();
    }

    @Override
    public Reservation createReservation(Long clientId, Long vehiculeId, LocalDate dateDebut, LocalDate dateFin) {
        return null;
    }

    @Override
    public void deleteReservation(Long id) {

    }

    @Override
    public double calculerMontantTotal(Reservation reservation) {
        return 0;
    }

    @Override
    public void annulerReservation(Long id) {

    }
}
