package com.example.archi_comp_wip.controller;

import com.example.archi_comp_wip.model.Flight;
import com.example.archi_comp_wip.model.Passenger;
import com.example.archi_comp_wip.model.Ticket;
import com.example.archi_comp_wip.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/")
    public String home(Model model) {
        List<Flight> flights = flightService.getAllFlights();
        if (flights == null || flights.isEmpty()) {
            System.out.println("Aucun vol trouvé");
        } else {
            System.out.println("Vols récupérés : " + flights.size());
        }
        model.addAttribute("flights", flights);
        return "Home";
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