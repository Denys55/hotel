package com.ithotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Room implements BaseEntity<Integer>, Serializable {
    private Integer id;
    private int numbersOfBed;
    private BigDecimal price;
    private String name;
    private ClassRoom classOfRoom;
    private StatusRoom statusOfRoom;
    private List<Order> orders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumbersOfBed() {
        return numbersOfBed;
    }

    public void setNumbersOfBed(int numbersOfBed) {
        this.numbersOfBed = numbersOfBed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClassRoom getClassOfRoom() {
        return classOfRoom;
    }

    public void setClassOfRoom(int id) {
        if (id == 1){
            this.classOfRoom = ClassRoom.ECONOMY;
        }
        else if (id == 2){
            this.classOfRoom = ClassRoom.STANDARD;
        }
        else if (id == 3){
            this.classOfRoom = ClassRoom.LUX;
        }
        else {
            throw new IllegalArgumentException("Incorrect value, do not have class for this"+ id);
        }
    }

    public StatusRoom getStatusOfRoom() {
        return statusOfRoom;
    }

    public void setStatusOfRoom(int id) {
        if (id == 1){
            this.statusOfRoom = StatusRoom.FREE;
        }
        else if (id == 2){
            this.statusOfRoom = StatusRoom.BOOKED;
        }
        else if (id == 3){
            this.statusOfRoom = StatusRoom.BUSY;
        }
        else if (id == 4){
            this.statusOfRoom = StatusRoom.NOT_AVAILABLE;
        }
        else {
            throw new IllegalArgumentException("Incorrect value, do not have class for this"+ id);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return numbersOfBed == room.numbersOfBed && Objects.equals(price, room.price) && Objects.equals(name, room.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbersOfBed, price, name);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numbersOfBed=" + numbersOfBed +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", classOfRoom=" + classOfRoom +
                ", statusesOfRoom=" + statusOfRoom +
                ", orders=" + orders +
                '}';
    }
}
