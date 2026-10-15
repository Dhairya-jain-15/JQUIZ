package com.firstspring.springbootdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String googleId;
    private String name;
    @Column(unique = true)
    private String email;
    private String picture;
    private String provider;
}
