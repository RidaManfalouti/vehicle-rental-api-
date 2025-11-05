 package com.example.location.controller;

    import com.example.location.entity.Vehicule;
    import com.example.location.service.VehiculeService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/vehicule")
    @RequiredArgsConstructor
    public class VehiculeController {

        private final VehiculeService vehiculeService;

        @GetMapping()
        public List<Vehicule> getAllVehicules() throws Exception {
            return vehiculeService.getAllVehicules();
        }

        @GetMapping("/{id}")
        public Vehicule getVehiculeById(@PathVariable Long id) {
            return vehiculeService.getVehiculeById(id);
        }

        @PostMapping()
        public Vehicule addVehicule(@RequestBody Vehicule vehicule) {
            return vehiculeService.addVehicule(vehicule);
        }

        @PutMapping("/{id}")
        public Vehicule updateVehicule(@PathVariable Long id, @RequestBody Vehicule vehicule) {
            return vehiculeService.updateVehicule(id, vehicule);
        }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteVehicule(@PathVariable Long id) {
            vehiculeService.deleteVehicule(id);
        }

        @GetMapping("/disponibles")
        public List<Vehicule> getVehiculesDisponibles() {
            return vehiculeService.getVehiculesDisponibles();
        }

        @PutMapping("/{id}/disponibilite")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void setVehiculeDisponibilite(@PathVariable Long id, @RequestParam boolean disponible) {
            vehiculeService.setVehiculeDisponibilite(id, disponible);
        }
    }