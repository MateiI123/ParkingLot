package com.proiect.parkinglotproject.common;

import com.proiect.parkinglotproject.entities.Cars;

import java.util.ArrayList;
import java.util.List;

public class CarDto {

    Long id;
    String licensePlate;
    String parkingSpot;
    String ownerName;

    public CarDto(Long id, String ownerName, String parkingSpot, String licensePlate) {
        this.id = id;
        this.ownerName = ownerName;
        this.parkingSpot = parkingSpot;
        this.licensePlate = licensePlate;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }



}
