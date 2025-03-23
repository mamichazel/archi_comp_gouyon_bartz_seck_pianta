package com.example.archi_comp_wip.service;

import com.example.archi_comp_wip.model.Plane;
import com.example.archi_comp_wip.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    public List<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

    public Optional<Plane> getPlaneById(Long id) {
        return planeRepository.findById(id);
    }

    public Plane createPlane(Plane plane) {
        return planeRepository.save(plane);
    }

    public void deletePlane(Long id) {
        planeRepository.deleteById(id);
    }
}