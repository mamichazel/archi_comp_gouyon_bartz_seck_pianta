package com.example.archi_comp_wip.service;

import com.example.archi_comp_wip.model.Airline;
import com.example.archi_comp_wip.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    public Optional<Airline> getAirlineById(Long id) {
        return airlineRepository.findById(id);
    }

    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

    public void deleteAirline(Long id) {
        airlineRepository.deleteById(id);
    }
}
