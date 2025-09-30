package com.airline.service;

import com.airline.model.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing Flight entities.
 * Defines the contract for flight-related operations.
 */
public interface FlightService {

    /**
     * Adds a new flight to the system
     * 
     * @param flight The flight object to be added
     * @return The saved flight with generated ID
     */
    Flight addFlight(Flight flight);

    /**
     * Retrieves all flights from the system
     * 
     * @return List of all flights
     */
    List<Flight> getAllFlight();

    /**
     * Retrieves a specific flight by its ID
     * 
     * @param flightId The ID of the flight to retrieve
     * @return The flight with the specified ID
     * @throws Exception if the flight with the given ID is not found
     */
    Flight getFlight(int flightId) throws Exception;

    /**
     * Deletes a flight by its ID
     * 
     * @param flightId The ID of the flight to delete
     * @return true if deletion was successful
     */
    boolean deleteFlight(int flightId);

    /**
     * Updates an existing flight
     * 
     * @param flightId The ID of the flight to update
     * @param flight The updated flight information
     * @return The updated flight
     */
    Flight updateFlight(int flightId, Flight flight);
}
