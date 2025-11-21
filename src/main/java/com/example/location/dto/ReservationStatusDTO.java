package com.example.location.dto;

import com.example.location.entity.StatusReservation;
import com.example.location.entity.Vehicule;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationStatusDTO {

    private String vehiculeModele;
    private StatusReservation status;

}