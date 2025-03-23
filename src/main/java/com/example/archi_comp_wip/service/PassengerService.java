package com.example.archi_comp_wip.service;

import com.example.archi_comp_wip.model.Passenger;
import com.example.archi_comp_wip.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    public Passenger createPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        return passengerRepository.findById(id).map(passenger -> {
            passenger.setNom(updatedPassenger.getNom());
            passenger.setPrenom(updatedPassenger.getPrenom());
            passenger.setEmail(updatedPassenger.getEmail());
            passenger.setNumeroPasseport(updatedPassenger.getNumeroPasseport());
            return passengerRepository.save(passenger);
        }).orElseThrow(() -> new RuntimeException("Passager non trouvé"));
    }

    public void deletePassenger(Long id) {
        passengerRepository.deleteById(id);
    }
}
