package com.airline.model;

import jakarta.persistence.*;

/**
 * Flight entity class that represents the flight table in the database.
 * Contains information about flights including ID, name, source, destination, and ticket price.
 */
@Entity
@Table(name = "flight")
public class Flight {

    /**
     * Unique identifier for the flight, auto-generated
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flightId;
    
    /**
     * Name of the flight
     */
    private String flightName;
    
    /**
     * Departure location of the flight
     */
    private String source;
    
    /**
     * Arrival location of the flight
     */
    private String Destination;
    
    /**
     * Price of the flight ticket
     */
    private Double ticketPrice;

    /**
     * Default constructor required by JPA
     */
    public Flight(){}

    /**
     * Parameterized constructor to create a flight with all attributes
     * 
     * @param flightId Unique identifier for the flight
     * @param flightName Name of the flight
     * @param source Departure location
     * @param destination Arrival location
     * @param ticketPrice Price of the ticket
     */
    public Flight(int flightId, String flightName, String source, String destination, Double ticketPrice) {
        this.flightId = flightId;
        this.flightName = flightName;
        this.source = source;
        Destination = destination;
        this.ticketPrice = ticketPrice;
    }

    /**
     * Get the flight ID
     * @return the flight ID
     */
    public int getFlightId() {
        return flightId;
    }

    /**
     * Set the flight ID
     * @param flightId the flight ID to set
     */
    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    /**
     * Get the flight name
     * @return the flight name
     */
    public String getFlightName() {
        return flightName;
    }

    /**
     * Set the flight name
     * @param flightName the flight name to set
     */
    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    /**
     * Get the source/departure location
     * @return the source location
     */
    public String getSource() {
        return source;
    }

    /**
     * Set the source/departure location
     * @param source the source location to set
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Get the destination/arrival location
     * @return the destination location
     */
    public String getDestination() {
        return Destination;
    }

    /**
     * Set the destination/arrival location
     * @param destination the destination location to set
     */
    public void setDestination(String destination) {
        Destination = destination;
    }

    /**
     * Get the ticket price
     * @return the ticket price
     */
    public Double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Set the ticket price
     * @param ticketPrice the ticket price to set
     */
    public void setTicketPrice(Double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    /**
     * Compares this flight with another object for equality
     * 
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (flightId != flight.flightId) return false;
        if (flightName != null ? !flightName.equals(flight.flightName) : flight.flightName != null) return false;
        if (source != null ? !source.equals(flight.source) : flight.source != null) return false;
        if (Destination != null ? !Destination.equals(flight.Destination) : flight.Destination != null) return false;
        return ticketPrice != null ? ticketPrice.equals(flight.ticketPrice) : flight.ticketPrice == null;
    }

    /**
     * Generates a hash code for this flight
     * 
     * @return the hash code value for this flight
     */
    @Override
    public int hashCode() {
        int result = flightId;
        result = 31 * result + (flightName != null ? flightName.hashCode() : 0);
        result = 31 * result + (source != null ? source.hashCode() : 0);
        result = 31 * result + (Destination != null ? Destination.hashCode() : 0);
        result = 31 * result + (ticketPrice != null ? ticketPrice.hashCode() : 0);
        return result;
    }
    
    /**
     * Returns a string representation of this flight
     * 
     * @return a string representation of this flight
     */
    @Override
    public String toString() {
        return "Flight{" +
                "flightId=" + flightId +
                ", flightName='" + flightName + '\'' +
                ", source='" + source + '\'' +
                ", Destination='" + Destination + '\'' +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
