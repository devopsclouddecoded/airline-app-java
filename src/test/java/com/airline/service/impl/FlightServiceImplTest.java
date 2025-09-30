package com.airline.service.impl;

import com.airline.model.Flight;
import com.airline.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FlightServiceImplTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightServiceImpl flightService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddFlight() {
        // Arrange
        Flight flight = new Flight(1, "TestFlight", "Source", "Destination", 100.0);
        when(flightRepository.save(flight)).thenReturn(flight);

        // Act
        Flight result = flightService.addFlight(flight);

        // Assert
        assertNotNull(result);
        assertEquals(flight, result);
        verify(flightRepository, times(1)).save(flight);
        verifyNoMoreInteractions(flightRepository);
    }

    @Test
    void testGetAllFlight() {
        // Arrange
        Flight flight1 = new Flight(1, "Flight1", "Source1", "Destination1", 100.0);
        Flight flight2 = new Flight(2, "Flight2", "Source2", "Destination2", 200.0);
        List<Flight> expectedFlights = Arrays.asList(flight1, flight2);
        
        when(flightRepository.findAll()).thenReturn(expectedFlights);

        // Act
        List<Flight> result = flightService.getAllFlight();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedFlights, result);
        verify(flightRepository, times(1)).findAll();
        verifyNoMoreInteractions(flightRepository);
    }

    @Test
    void testGetFlight_Success() throws Exception {
        // Arrange
        int flightId = 1;
        Flight expectedFlight = new Flight(flightId, "TestFlight", "Source", "Destination", 100.0);
        
        when(flightRepository.findById(flightId)).thenReturn(Optional.of(expectedFlight));

        // Act
        Flight result = flightService.getFlight(flightId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedFlight, result);
        verify(flightRepository, times(1)).findById(flightId);
        verifyNoMoreInteractions(flightRepository);
    }

    @Test
    void testGetFlight_NotFound() {
        // Arrange
        int flightId = 999;
        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> {
            flightService.getFlight(flightId);
        });
        
        assertTrue(exception.getMessage().contains("No Flight with Id: " + flightId));
        verify(flightRepository, times(1)).findById(flightId);
        verifyNoMoreInteractions(flightRepository);
    }

    @Test
    void testDeleteFlight() {
        // Arrange
        int flightId = 1;
        doNothing().when(flightRepository).deleteById(flightId);

        // Act
        boolean result = flightService.deleteFlight(flightId);

        // Assert
        assertTrue(result);
        verify(flightRepository, times(1)).deleteById(flightId);
        verifyNoMoreInteractions(flightRepository);
    }

    @Test
    void testUpdateFlight() {
        // Arrange
        int flightId = 1;
        Flight flight = new Flight(flightId, "UpdatedFlight", "UpdatedSource", "UpdatedDestination", 150.0);
        when(flightRepository.save(flight)).thenReturn(flight);

        // Act
        Flight result = flightService.updateFlight(flightId, flight);

        // Assert
        assertNotNull(result);
        assertEquals(flight, result);
        verify(flightRepository, times(1)).save(flight);
        verifyNoMoreInteractions(flightRepository);
    }
}
