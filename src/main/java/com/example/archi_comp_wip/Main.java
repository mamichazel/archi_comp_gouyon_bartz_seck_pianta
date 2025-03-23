package com.example.archi_comp_wip;

import com.example.archi_comp_wip.model.*;
import com.example.archi_comp_wip.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private PlaneService planeService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private PassengerService passengerService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private LuggageService luggageService;

    @Autowired
    private TicketService ticketService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initializeTables();
    }

    private void initializeTables() {
        // Initialiser Airline
        Airline airline = new Airline();
        airline.setNom("Initialise");
        airline.setCodeIATA("Initialise");
        airline = airlineService.createAirline(airline);

        // Initialiser Plane
        Plane plane = new Plane();
        plane.setNumeroImmatriculation("Initialise");
        plane.setModele("Initialise");
        plane.setCapacite(1);
        plane.setAirline(airline);
        plane = planeService.createPlane(plane);

        // Initialiser Personnel
        Personnel personnel = new Personnel();
        personnel.setNom("Initialise");
        personnel.setPrenom("Initialise");
        personnel.setRole("Initialise_pilot");
        personnel = personnelService.createPersonnel(personnel);

        // Initialiser Flight
        Flight flight = new Flight();
        flight.setNumeroVol("Initialise");
        flight.setDateDepart(LocalDateTime.of(1970, 1, 1, 0, 0));
        flight.setDateArrivee(LocalDateTime.of(1970, 1, 1, 2, 0));
        flight.setStatut("Initialise");
        flight.setPlane(plane);
        flight.setAirline(airline);
        flight.getPersonnel().add(personnel);
        flight = flightService.createFlight(flight);

        // Initialiser Passenger
        Passenger passenger = new Passenger();
        passenger.setNom("Initialise");
        passenger.setPrenom("Initialise");
        passenger.setEmail("Initialise@Initialise.com");
        passenger.setNumeroPasseport("Initialise");
        passenger = passengerService.createPassenger(passenger);

        // Initialiser Ticket
        Ticket ticket = new Ticket();
        ticket.setNumeroTicket("Initialise");
        ticket.setClasse("Initialise");
        ticket.setPrix(9.99);
        ticket.setPassenger(passenger);
        ticket.setFlight(flight);
        ticket = ticketService.createTicket(ticket);

        // Initialiser Luggage
        Luggage luggage = new Luggage();
        luggage.setPoids(1.1);
        luggage.setType("Initialise");
        luggage.setPassenger(passenger);
        luggage = luggageService.createLuggage(luggage);

        System.out.println("Toutes les tables ont été initialisées avec une entrée.");
    }

}