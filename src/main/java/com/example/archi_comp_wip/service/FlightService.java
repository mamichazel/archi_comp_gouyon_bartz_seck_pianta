package com.example.archi_comp_wip.service;

import com.example.archi_comp_wip.model.Flight;
import com.example.archi_comp_wip.model.Personnel;
import com.example.archi_comp_wip.repository.FlightRepository;
import com.example.archi_comp_wip.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PersonnelRepository personnelRepository;

    public List<Flight> getAllFlights() {
        try {
            return flightRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des vols: " + e.getMessage(), e);
        }
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(Flight flight) {
        List<Personnel> personnels = new ArrayList<>();
        for (Personnel p : flight.getPersonnel()) {
            personnels.add(personnelRepository.findById(p.getId()).orElseThrow(() -> new RuntimeException("Personnel not found")));
        }
        flight.setPersonnel(personnels);

        return flightRepository.save(flight);
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}