package com.example.webgame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer gameId;
    public String gameName;
    public String gameType;
    public String gameDescription;
    public LocalDateTime releaseDate;
    public String releaseLocation;
    public String photos;
}
