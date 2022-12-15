package com.example.webgame.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    @Column(name = "game_description", nullable = false, length = 2000)
    public String gameDescription;
    public LocalDateTime releaseDate;
    public String releaseLocation;
    @ManyToOne
    @JoinColumn(name="typeId", nullable = false)
    private GameType type;
}
