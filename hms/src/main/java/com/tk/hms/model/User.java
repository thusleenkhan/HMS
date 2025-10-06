package com.tk.hms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    
    @PositiveOrZero(message = "Please enter zero or a positive integer value")
    private int role;
    private boolean active;

    // Getter and Setter for id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Getter and Setter for userName
    public String getUsername() {
        return username;
    }
    public void setUsername(String userName) {
        this.username = userName;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for role
    public int getRole() {
        return role;
    }
    public void setRole(int role) {
        this.role = role;
    }

    // Getter and Setter for active
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
}
