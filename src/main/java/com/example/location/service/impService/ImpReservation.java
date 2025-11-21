package com.example.location.service.impService;

import com.example.location.dto.ReservationStatusDTO;
import com.example.location.entity.Reservation;
import com.example.location.entity.StatusReservation;
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

    // ----------------------------------------------------------
    // GET ALL
    // ----------------------------------------------------------
    @Override
    public List<Reservation> getAllReservations() throws Exception {
        List<Reservation> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            throw new Exception("Aucune réservation trouvée");
        }
        return reservations;
    }

    // ----------------------------------------------------------
    // GET BY ID
    // ----------------------------------------------------------
    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Reservation avec id " + id + " introuvable"));
    }

    // ----------------------------------------------------------
    // GET BY STATUS
    // ----------------------------------------------------------
    @Override
    public List<ReservationStatusDTO> getAllReservationByStatus(StatusReservation status) {

        return reservationRepository.findAll().stream()
                .filter(r -> r.getStatusReservation() == status)
                .map(r -> new ReservationStatusDTO(
                        r.getVehicule().getModele(),
                        r.getStatusReservation()
                ))
                .toList();
    }

    // ----------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------
    @Override
    public Reservation createReservation(Long clientId, Long vehiculeId, LocalDate dateDebut, LocalDate dateFin) {

        var client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

        var vehicule = vehiculeRepository.findById(vehiculeId)
                .orElseThrow(() -> new IllegalArgumentException("Véhicule introuvable"));

        if (!vehicule.isDisponible()) {
            throw new IllegalStateException("Véhicule non disponible");
        }

        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setVehicule(vehicule);
        reservation.setDateDebut(dateDebut);
        reservation.setDateFin(dateFin);

        reservation.setMontantTotal(calculerMontantTotal(reservation));
        reservation.setStatusReservation(StatusReservation.PENDING);

        vehicule.setDisponible(false);
        vehiculeRepository.save(vehicule);

        return reservationRepository.save(reservation);
    }

    // ----------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------
    @Override
    public void deleteReservation(Long id) {
        Reservation r = getReservationById(id);
        reservationRepository.delete(r);
    }

    @Override
    public double calculerMontantTotal(Reservation reservation) {
        long nbJours = reservation.getDateFin().toEpochDay() - reservation.getDateDebut().toEpochDay();
        return reservation.getVehicule().getPrixParJour() * nbJours;
    }

    @Override
    public void annulerReservation(Long id) {

        Reservation reservation = getReservationById(id);

        var vehicule = reservation.getVehicule();
        vehicule.setDisponible(true);
        vehiculeRepository.save(vehicule);

        reservation.setStatusReservation(StatusReservation.CANCELLED);
        reservationRepository.save(reservation);
    }
}
