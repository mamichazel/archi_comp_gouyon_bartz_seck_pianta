package com.example.archi_comp_wip.controller;

import com.example.archi_comp_wip.model.*;
import com.example.archi_comp_wip.service.AirlineService;
import com.example.archi_comp_wip.service.FlightService;
import com.example.archi_comp_wip.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private PlaneService planeService;

    @GetMapping("/")
    public String home(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        if (flights == null || flights.isEmpty()) {
            System.out.println("Aucun vol trouvé");
        } else {
            System.out.println("Vols récupérés : " + flights.size());
        }

        List<Airline> airlines = airlineService.getAllAirlines();
        List<Plane> planes = planeService.getAllPlanes();

        model.addAttribute("flights", flights);
        model.addAttribute("airlines", airlines);
        model.addAttribute("planes", planes);

        return "Home";
    }

    @PostMapping("/addFlight")
    public String addFlight(@RequestParam String numeroVol,
                            @RequestParam String dateDepart,
                            @RequestParam String dateArrivee,
                            @RequestParam String statut,
                            @RequestParam Long airline,
                            @RequestParam Long plane) {

        Flight flight = new Flight();
        flight.setNumeroVol(numeroVol);
        flight.setDateDepart(LocalDateTime.parse(dateDepart));
        flight.setDateArrivee(LocalDateTime.parse(dateArrivee));
        flight.setStatut(statut);

        Airline selectedAirline = airlineService.getAirlineById(airline)
                .orElseThrow(() -> new RuntimeException("Airline not found"));
        Plane selectedPlane = planeService.getPlaneById(plane)
                .orElseThrow(() -> new RuntimeException("Plane not found"));

        flight.setAirline(selectedAirline);
        flight.setPlane(selectedPlane);

        flightService.saveFlight(flight);

        return "redirect:/";
    }

    @GetMapping("/flights/{id}/passengers")
    public String showPassengers(@PathVariable Long id, Model model) {
        Flight flight = flightService.getFlightById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));

        List<Ticket> tickets = flight.getTickets();
        List<Passenger> passengers = tickets.stream()
                .map(Ticket::getPassenger)
                .collect(Collectors.toList());

        model.addAttribute("flight", flight);
        model.addAttribute("passengers", passengers);

        return "Passengers";
    }

}