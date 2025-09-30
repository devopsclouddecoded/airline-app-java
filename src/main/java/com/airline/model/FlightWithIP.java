package com.airline.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) that wraps a Flight entity along with the server IP address.
 * Used to return flight information along with the server IP in API responses.
 */
@Data
@AllArgsConstructor
@Setter
@Getter
public class FlightWithIP {
    /**
     * The flight entity
     */
    private Flight flight;
    
    /**
     * The IP address of the server handling the request
     */
    private String serverIP;
}