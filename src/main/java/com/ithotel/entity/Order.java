package com.ithotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Order implements BaseEntity<Integer>, Serializable {
    private Integer id;
    private int numbersOfPlace;
    private Date timeOrder;
    private Date checkIN;
    private Date checkOut;
    private ClassRoom classOfRoom;
    private StatusOfOrder statusOfOrder;
    private BigDecimal totalCost;
    private List<Room> rooms;
    private List<User> users;
    private int roomId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumbersOfPlace() {
        return numbersOfPlace;
    }

    public void setNumbersOfPlace(int numbersOfPlace) {
        this.numbersOfPlace = numbersOfPlace;
    }

    public Date getTimeOrder() {
        return timeOrder;
    }

    public void setTimeOrder(Date timeOrder) {
        this.timeOrder = timeOrder;
    }

    public Date getCheckIN() {
        return checkIN;
    }

    public void setCheckIN(Date checkIN) {
        this.checkIN = checkIN;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public ClassRoom getClassOfRoom() {
        return classOfRoom;
    }

    public void setClassOfRoom(String s) {

        if (s.equalsIgnoreCase("economy")) {
            this.classOfRoom = ClassRoom.ECONOMY;
        }

        else if (s.equalsIgnoreCase("standard")) {
            this.classOfRoom = ClassRoom.STANDARD;
        }

        else if (s.equalsIgnoreCase("lux")) {
            this.classOfRoom = ClassRoom.LUX;
        }

        else {
            throw new IllegalArgumentException("Incorrect value, do not have class for this"+ s);
        }
    }

    public void setClassOfRoomById(int id) {

        if (id == 1) {
            this.classOfRoom = ClassRoom.ECONOMY;
        }

        else if (id == 2) {
            this.classOfRoom = ClassRoom.STANDARD;
        }

        else if (id == 3) {
            this.classOfRoom = ClassRoom.LUX;
        }

        else {
            throw new IllegalArgumentException("Incorrect value, do not have class for this id" + id);
        }
    }

    public StatusOfOrder getStatusOfOrder() {
        return statusOfOrder;
    }

    public void setStatusOfOrder(int id) {

        if (id == 1) {
            this.statusOfOrder = StatusOfOrder.NEW;
        } else if (id == 2) {
            this.statusOfOrder = StatusOfOrder.ON_CONFIRMATION;
        } else if (id == 3) {
            this.statusOfOrder = StatusOfOrder.INVOICED;
        } else if (id == 4) {
            this.statusOfOrder = StatusOfOrder.PAID;
        } else if (id == 5) {
            this.statusOfOrder = StatusOfOrder.CANCELED;
        } else {
            throw new IllegalArgumentException("Incorrect value, do not have class for this" + id);
        }
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(timeOrder, order.timeOrder) && Objects.equals(totalCost, order.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeOrder, totalCost);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", numbersOfPlace=" + numbersOfPlace +
                ", timeOrder=" + timeOrder +
                ", checkIN=" + checkIN +
                ", checkOut=" + checkOut +
                ", classOfRoom=" + classOfRoom +
                ", statusOfOrder=" + statusOfOrder +
                ", totalCost=" + totalCost +
                ", rooms=" + rooms +
                '}';
    }
}
