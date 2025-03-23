package com.example.archi_comp_wip.controller;

import com.example.archi_comp_wip.model.Flight;
import com.example.archi_comp_wip.model.Personnel;
import com.example.archi_comp_wip.service.FlightService;
import com.example.archi_comp_wip.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PersonnelService personnelService;

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public Optional<Flight> getFlightById(@PathVariable Long id) {
        return flightService.getFlightById(id);
    }

    @PostMapping
    public Flight createFlight(@RequestBody Flight flight) {
        return flightService.createFlight(flight);
    }

    @PostMapping("/{flights_id}/personnel")
    public ResponseEntity<Flight> addPersonnelToFlight(@PathVariable Long flightId, @RequestBody Map<String, Long> body) {
        Long personnelId = body.get("personnelId");

        // Vérifier si le vol et le personnel existent
        Flight flight = flightService.getFlightById(flightId).orElse(null);
        Personnel personnel = personnelService.getPersonnelById(personnelId).orElse(null);

        if (flight == null || personnel == null) {
            return ResponseEntity.notFound().build();
        }

        // Ajouter le personnel au vol
        flight.getPersonnel().add(personnel);
        flightService.createFlight(flight);

        return ResponseEntity.ok(flight);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

}
