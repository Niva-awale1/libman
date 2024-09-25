package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "users") // Specify the table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Long id;

    private String name;
    private String email;
    private String password; // This should be encrypted later

    // Many users can have one membership type
    @ManyToOne
    @JoinColumn(name = "membership_id") // Foreign key for membership
    private Membership membership;

    // Many users can have one role
    @ManyToOne
    @JoinColumn(name = "role_id") // Foreign key for role
    private Role role;

    // Default constructor
    public User() {
    }

    // Parameterized constructor for registration
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Parameterized constructor for full user
    public User(String name, String email, String password, Role role, Membership membership) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.membership = membership;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}