package com.airline.repository;

import com.airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Flight entity.
 * Extends JpaRepository to inherit basic CRUD operations.
 * The Integer type parameter refers to the type of the primary key (flightId).
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight,Integer> {
    // No additional methods needed as basic CRUD operations are inherited from JpaRepository
}
