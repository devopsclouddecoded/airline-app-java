package com.airline.service.impl;

import com.airline.model.Flight;
import com.airline.repository.FlightRepository;
import com.airline.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the FlightService interface.
 * Provides the business logic for flight-related operations.
 */
@Service
public class FlightServiceImpl implements FlightService {
    
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(FlightServiceImpl.class);

    /**
     * Repository for Flight entity operations
     */
    @Autowired
    private FlightRepository flightRepository;

    /**
     * {@inheritDoc}
     * Saves a new flight to the database
     */
    @Override
    public Flight addFlight(Flight flight) {
        logger.info("Adding new flight: {}", flight);
        Flight savedFlight = flightRepository.save(flight);
        logger.info("Flight saved with ID: {}", savedFlight.getFlightId());
        return savedFlight;
    }

    /**
     * {@inheritDoc}
     * Retrieves all flights from the database
     */
    @Override
    public List<Flight> getAllFlight() {
        logger.info("Retrieving all flights");
        List<Flight> flights = flightRepository.findAll();
        logger.info("Retrieved {} flights from database", flights.size());
        return flights;
    }

    /**
     * {@inheritDoc}
     * Retrieves a specific flight by ID or throws an exception if not found
     */
    @Override
    public Flight getFlight(int flightId) throws Exception {
        logger.info("Retrieving flight with ID: {}", flightId);
        try {
            Flight flight = flightRepository.findById(flightId).orElseThrow(
                () -> new Exception("No Flight with Id: " + flightId)
            );
            logger.info("Successfully retrieved flight with ID: {}", flightId);
            return flight;
        } catch (Exception e) {
            logger.error("Error retrieving flight with ID {}: {}", flightId, e.getMessage());
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     * Deletes a flight by ID
     */
    @Override
    public boolean deleteFlight(int flightId) {
        logger.info("Deleting flight with ID: {}", flightId);
        try {
            flightRepository.deleteById(flightId);
            logger.info("Successfully deleted flight with ID: {}", flightId);
            return true;
        } catch (Exception e) {
            logger.error("Error deleting flight with ID {}: {}", flightId, e.getMessage());
            throw e;
        }
    }

    /**
     * {@inheritDoc}
     * Updates an existing flight in the database
     */
    @Override
    public Flight updateFlight(int flightId, Flight flight) {
        logger.info("Updating flight with ID: {}", flightId);
        logger.debug("Update data: {}", flight);
        try {
            Flight updatedFlight = flightRepository.save(flight);
            logger.info("Successfully updated flight with ID: {}", flightId);
            return updatedFlight;
        } catch (Exception e) {
            logger.error("Error updating flight with ID {}: {}", flightId, e.getMessage());
            throw e;
        }
    }


}
