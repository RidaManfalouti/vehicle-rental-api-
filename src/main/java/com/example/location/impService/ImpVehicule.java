package com.example.location.impService;

import com.example.location.entity.Vehicule;
import com.example.location.repository.VehiculeRepository;
import com.example.location.service.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImpVehicule implements VehiculeService {
    private final VehiculeRepository vehiculeRepository;
    @Override
    public List<Vehicule> getAllVehicules() throws Exception {
        if (vehiculeRepository.findAll().isEmpty()) {
            throw new Exception("pas des vehicules exist");
        }
        return vehiculeRepository.findAll();
    }

    @Override
    public Vehicule getVehiculeById(Long id) {
        if (vehiculeRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Vehicule with id " + id + " not found");
        }
        return vehiculeRepository.findById(id).get();
    }

    @Override
    public Vehicule addVehicule(Vehicule vehicule) {
            return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule updateVehicule(Long id, Vehicule vehicule) {
        if (vehiculeRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Vehicule with id " + id + " not found");
        }
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public void deleteVehicule(Long id) {
        if (vehiculeRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Vehicule with id " + id + " not found");
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public List<Vehicule> getVehiculesDisponibles() {
        return vehiculeRepository.findByDisponibleTrue();
    }

    @Override
    public void setVehiculeDisponibilite(Long id, boolean disponible) {
        Vehicule vehicule = getVehiculeById(id);
        vehicule.setDisponible(disponible);
        vehiculeRepository.save(vehicule);
    }
}
