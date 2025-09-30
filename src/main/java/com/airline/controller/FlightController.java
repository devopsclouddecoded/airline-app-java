package com.airline.controller;

import com.airline.model.Flight;
import com.airline.model.FlightWithIP;
import com.airline.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller for handling flight-related HTTP requests.
 * Provides endpoints for CRUD operations on Flight entities.
 */
@RestController
@RequestMapping("/flight")
@CrossOrigin(origins = "*") // Allows requests from any origin
public class FlightController {
    
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(FlightController.class);

    /**
     * Flight service dependency for handling business logic
     */
    @Autowired
    private FlightService flightService;

    /**
     * Endpoint to add a new flight
     * 
     * @param flight The flight object to be added
     * @return ResponseEntity containing the created flight and HTTP status 201 (CREATED)
     */
    @PostMapping("/")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight){
        logger.info("Adding new flight: {}", flight);
        try {
            Flight flight1 = flightService.addFlight(flight);
            logger.info("Successfully added flight with ID: {}", flight1.getFlightId());
            return new ResponseEntity<>(flight1, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error adding flight: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Original method to get all flights (commented out)
     */
//    @GetMapping("/")
//    public ResponseEntity<List<Flight>> getAllFlight(){
//        List<Flight> allFlight = flightService.getAllFlight();
//        return new ResponseEntity<>(allFlight,HttpStatus.OK);
//    }

    /**
     * Enhanced endpoint to get all flights with server IP information
     * 
     * @return ResponseEntity containing a list of flights with server IP and HTTP status 200 (OK)
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    @GetMapping("/")
    public ResponseEntity<List<FlightWithIP>> getAllFlight() throws UnknownHostException {
        logger.info("Fetching all flights");
        try {
            // Get all flights from the service
            List<Flight> allFlights = flightService.getAllFlight();
            logger.debug("Retrieved {} flights from database", allFlights.size());
            
            // Get the server's IP address
            String serverIP = InetAddress.getLocalHost().getHostAddress();
            logger.debug("Server IP: {}", serverIP);

            // Transform each Flight into a FlightWithIP object
            List<FlightWithIP> flightsWithIP = allFlights.stream()
                    .map(flight -> new FlightWithIP(flight, serverIP))
                    .collect(Collectors.toList());

            logger.info("Successfully retrieved all flights with server IP");
            return new ResponseEntity<>(flightsWithIP, HttpStatus.OK);
        } catch (UnknownHostException e) {
            logger.error("Error resolving host address: {}", e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Error fetching all flights: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Endpoint to get a specific flight by its ID
     * 
     * @param flightId The ID of the flight to retrieve
     * @return ResponseEntity containing the flight and HTTP status 200 (OK)
     * @throws Exception if the flight with the given ID is not found
     */
    @GetMapping("/{flightId}")
    public ResponseEntity<Flight> getFlight(@PathVariable("flightId") int flightId) throws Exception {
        logger.info("Fetching flight with ID: {}", flightId);
        try {
            Flight flight = flightService.getFlight(flightId);
            logger.info("Successfully retrieved flight with ID: {}", flightId);
            return ResponseEntity.ok(flight);
        } catch (Exception e) {
            logger.error("Error fetching flight with ID {}: {}", flightId, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Endpoint to delete a flight by its ID
     * 
     * @param flightId The ID of the flight to delete
     * @return ResponseEntity containing a confirmation message and HTTP status 200 (OK)
     */
    @DeleteMapping("/{flightId}")
    public ResponseEntity<String> deleteFlight(@PathVariable("flightId") int flightId){
        logger.info("Deleting flight with ID: {}", flightId);
        try {
            flightService.deleteFlight(flightId);
            logger.info("Successfully deleted flight with ID: {}", flightId);
            return new ResponseEntity<String>("Flight ID: "+flightId,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error deleting flight with ID {}: {}", flightId, e.getMessage(), e);
            throw e;
        }
    }

    /**
     * Endpoint to update a flight by its ID
     * 
     * @param flightId The ID of the flight to update
     * @param flight The updated flight information
     * @return ResponseEntity containing the updated flight and HTTP status 200 (OK)
     * @throws Exception if the flight with the given ID is not found
     */
    @PutMapping("/{flightId}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("flightId") int flightId,@RequestBody Flight flight) throws Exception {
        logger.info("Updating flight with ID: {}", flightId);
        logger.debug("Update data: {}", flight);
        try {
            flight.setFlightId(flightId);
            Flight flight1 = flightService.updateFlight(flightId,flight);
            logger.info("Successfully updated flight with ID: {}", flightId);
            return new ResponseEntity<>(flight1,HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating flight with ID {}: {}", flightId, e.getMessage(), e);
            throw e;
        }
    }

}
