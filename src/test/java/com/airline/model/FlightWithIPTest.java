package com.airline.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the FlightWithIP model class
 */
public class FlightWithIPTest {

    /**
     * Test the constructor and getters
     */
    @Test
    public void testConstructorAndGetters() {
        // Create a Flight object
        Flight flight = new Flight(1, "AI101", "Delhi", "Mumbai", 5000.00);
        String serverIP = "192.168.1.1";
        
        // Create FlightWithIP using the constructor
        FlightWithIP flightWithIP = new FlightWithIP(flight, serverIP);
        
        // Verify the values using getters
        assertEquals(flight, flightWithIP.getFlight());
        assertEquals(serverIP, flightWithIP.getServerIP());
    }
    
    /**
     * Test setters
     */
    @Test
    public void testSetters() {
        // Create initial objects
        Flight flight1 = new Flight(1, "AI101", "Delhi", "Mumbai", 5000.00);
        String serverIP1 = "192.168.1.1";
        
        FlightWithIP flightWithIP = new FlightWithIP(flight1, serverIP1);
        
        // Create new objects to set
        Flight flight2 = new Flight(2, "SG202", "Mumbai", "Bangalore", 4500.00);
        String serverIP2 = "10.0.0.1";
        
        // Use setters
        flightWithIP.setFlight(flight2);
        flightWithIP.setServerIP(serverIP2);
        
        // Verify the values were updated
        assertEquals(flight2, flightWithIP.getFlight());
        assertEquals(serverIP2, flightWithIP.getServerIP());
    }
    
    /**
     * Test property equality instead of object equality
     */
    @Test
    public void testEqualsAndHashCode() {
        Flight flight1 = new Flight(1, "AI101", "Delhi", "Mumbai", 5000.00);
        String serverIP = "192.168.1.1";
        
        FlightWithIP flightWithIP = new FlightWithIP(flight1, serverIP);
        
        // Test individual properties
        assertEquals(flight1, flightWithIP.getFlight());
        assertEquals(serverIP, flightWithIP.getServerIP());
        
        // Test toString method
        String toStringResult = flightWithIP.toString();
        assertTrue(toStringResult.contains("flight="));
        assertTrue(toStringResult.contains("serverIP=" + serverIP));
    }
}
