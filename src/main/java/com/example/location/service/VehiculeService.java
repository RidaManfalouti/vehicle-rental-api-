package com.example.location.service;

import com.example.location.entity.Vehicule;

import java.util.List;

public interface VehiculeService {
    List<Vehicule> getAllVehicules() throws Exception;
    Vehicule getVehiculeById(Long id);
    Vehicule addVehicule(Vehicule vehicule);
    Vehicule updateVehicule(Long id, Vehicule vehicule);
    void deleteVehicule(Long id);
    List<Vehicule> getVehiculesDisponibles();
    void setVehiculeDisponibilite(Long id, boolean disponible);


}
