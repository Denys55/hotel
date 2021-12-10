package com.ithotel.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class User implements BaseEntity<Integer>, Serializable {
    private Integer id;
    private String login;
    private String password;
    private Role role;
    private List<Order> order;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(int id) {
        if (id == 1){
            this.role = Role.CLIENT;
        }
        else if (id == 2){
            this.role = Role.MANAGER;
        }
        else if (id == 3){
            this.role = Role.ADMIN;
        }
        else {
            throw new IllegalArgumentException("Incorrect id, do not have role for this"+id);
        }
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role + '\'' +
                ", balance=" + balance +
                '}';
    }
}
