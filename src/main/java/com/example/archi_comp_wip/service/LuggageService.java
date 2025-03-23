package com.example.archi_comp_wip.service;

import com.example.archi_comp_wip.model.Luggage;
import com.example.archi_comp_wip.repository.LuggageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LuggageService {

    @Autowired
    private LuggageRepository luggageRepository;

    public List<Luggage> getAllLuggages() {
        return luggageRepository.findAll();
    }

    public Optional<Luggage> getLuggageById(Long id) {
        return luggageRepository.findById(id);
    }

    public Luggage createLuggage(Luggage luggage) {
        return luggageRepository.save(luggage);
    }

    public void deleteLuggage(Long id) {
        luggageRepository.deleteById(id);
    }
}
