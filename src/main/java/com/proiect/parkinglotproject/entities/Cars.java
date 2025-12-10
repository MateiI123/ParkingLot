package com.proiect.parkinglotproject.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String LicensePlate;

    private String parkingSpot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner; // cheie primara in alt tabel

    public User getOwner() {
        return owner;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return LicensePlate;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

}