package com.example.webgame.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer userId;
    public String username;
    public String password;
    public String country;
    public LocalDateTime createAccountDate;
}
