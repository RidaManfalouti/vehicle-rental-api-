package com.example.location.controller;

import com.example.location.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static java.time.LocalDate.parse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {
    public final ReservationService reservationService;

    @GetMapping()
    public Object getAllReservations() throws Exception {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Object getReservationById(@PathVariable Long id) {
        return reservationService.getReservationById(id);
    }

    @PostMapping()
    public Object createReservation(@RequestBody Long clientId, @RequestBody Long vehiculeId,
                                    @RequestBody String dateDebut, @RequestBody String dateFin) {
        return reservationService.createReservation(clientId, vehiculeId,
                parse(dateDebut), parse(dateFin));
    }



}
