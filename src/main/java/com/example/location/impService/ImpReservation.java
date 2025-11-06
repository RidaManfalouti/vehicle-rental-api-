package com.example.location.impService;

import com.example.location.entity.Reservation;
import com.example.location.repository.ClientRepository;
import com.example.location.repository.ReservationRepository;
import com.example.location.repository.VehiculeRepository;
import com.example.location.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpReservation implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;

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
        var clientOpt = clientRepository.findById(clientId);
        if (clientOpt.isEmpty()) {
            throw new IllegalArgumentException("Client avec id " + clientId + " introuvable");
        }

        var vehiculeOpt = vehiculeRepository.findById(vehiculeId);
        if (vehiculeOpt.isEmpty()) {
            throw new IllegalArgumentException("Véhicule avec id " + vehiculeId + " introuvable");
        }

        var vehicule = vehiculeOpt.get();
        if (Boolean.FALSE.equals(vehicule.isDisponible())) {
            throw new IllegalStateException("Véhicule non disponible");
        }

        Reservation reservation = new Reservation();
        reservation.setClient(clientOpt.get());
        reservation.setVehicule(vehicule);
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);

        double montant = calculerMontantTotal(reservation);
        reservation.setMontantTotal(montant);

        vehicule.setDisponible(false);
        vehiculeRepository.save(vehicule);

        return reservationRepository.save(reservation);
    }
    @Override
    public void deleteReservation(Long id) {
        if (reservationRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Reservation with id " + id + " not found");
        }
        reservationRepository.deleteById(id);
    }

    @Override
    public double calculerMontantTotal(Reservation reservation) {
        return reservation.getVehicule().getPrixParJour() *
                (reservation.getDateFin().toEpochDay() - reservation.getDateDebut().toEpochDay());
    }

    @Override
    public void annulerReservation(Long id) {
        Reservation reservation = getReservationById(id);
        var vehicule = reservation.getVehicule();
        vehicule.setDisponible(true);
        vehiculeRepository.save(vehicule);
        reservationRepository.deleteById(id);
    }
}
