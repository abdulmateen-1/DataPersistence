package com.akindele.woek.DataStoragePersistence.entity;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

//Entity model for the delivery table
@NamedQuery(
        name = "Delivery.findAll",
        query = "select p from Delivery p where name = :name"
)
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "address_full", length = 512)
    private String address;

    @Nationalized
    private String name;
    private LocalDate date;
    private LocalTime time;

    private LocalDateTime deliveryTime;

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Type(type = "yes_no")
    private Boolean completed;

    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Plant> plants;

    public Delivery() {
    }

    public Delivery(Long id, String address, String name, LocalDate date, LocalTime time) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public Delivery(String name, String address, LocalDateTime deliveryTime) {
        this.name = name;
        this.address = address;
        this.deliveryTime = deliveryTime;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
