package com.example.archi_comp_wip.controller;

import com.example.archi_comp_wip.model.Luggage;
import com.example.archi_comp_wip.service.LuggageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/luggages")
public class LuggageController {

    @Autowired
    private LuggageService luggageService;

    @GetMapping
    public List<Luggage> getAllLuggages() {
        return luggageService.getAllLuggages();
    }

    @GetMapping("/{id}")
    public Optional<Luggage> getLuggageById(@PathVariable Long id) {
        return luggageService.getLuggageById(id);
    }

    @PostMapping
    public Luggage createLuggage(@RequestBody Luggage luggage) {
        return luggageService.createLuggage(luggage);
    }

    @DeleteMapping("/{id}")
    public void deleteLuggage(@PathVariable Long id) {
        luggageService.deleteLuggage(id);
    }
}
