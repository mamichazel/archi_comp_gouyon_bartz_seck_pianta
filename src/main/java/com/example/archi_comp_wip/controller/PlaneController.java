package com.example.archi_comp_wip.controller;

import com.example.archi_comp_wip.model.Plane;
import com.example.archi_comp_wip.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planes")
public class PlaneController {

    @Autowired
    private PlaneService planeService;

    @GetMapping
    public List<Plane> getAllPlanes() {
        return planeService.getAllPlanes();
    }

    @GetMapping("/{id}")
    public Optional<Plane> getPlaneById(@PathVariable Long id) {
        return planeService.getPlaneById(id);
    }

    @PostMapping
    public Plane createPlane(@RequestBody Plane plane) {
        return planeService.createPlane(plane);
    }

    @DeleteMapping("/{id}")
    public void deletePlane(@PathVariable Long id) {
        planeService.deletePlane(id);
    }
}