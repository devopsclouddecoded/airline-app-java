package com.airline.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Flight model class
 */
public class FlightTest {

    /**
     * Test the default constructor
     */
    @Test
    public void testDefaultConstructor() {
        Flight flight = new Flight();
        assertNotNull(flight);
    }

    /**
     * Test the parameterized constructor
     */
    @Test
    public void testParameterizedConstructor() {
        Flight flight = new Flight(1, "AI101", "Delhi", "Mumbai", 5000.00);
        
        assertEquals(1, flight.getFlightId());
        assertEquals("AI101", flight.getFlightName());
        assertEquals("Delhi", flight.getSource());
        assertEquals("Mumbai", flight.getDestination());
        assertEquals(5000.00, flight.getTicketPrice());
    }

    /**
     * Test getters and setters
     */
    @Test
    public void testGettersAndSetters() {
        Flight flight = new Flight();
        
        flight.setFlightId(2);
        flight.setFlightName("SG202");
        flight.setSource("Mumbai");
        flight.setDestination("Bangalore");
        flight.setTicketPrice(4500.00);
        
        assertEquals(2, flight.getFlightId());
        assertEquals("SG202", flight.getFlightName());
        assertEquals("Mumbai", flight.getSource());
        assertEquals("Bangalore", flight.getDestination());
        assertEquals(4500.00, flight.getTicketPrice());
    }
}
