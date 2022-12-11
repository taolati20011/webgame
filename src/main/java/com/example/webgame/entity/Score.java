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
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer scoreId;
    @ManyToOne
    @JoinColumn(name="gameId", nullable = false)
    public Game game;
    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    public User user;
    public String playerDisplayName;
    public LocalDateTime scoreDate;
}
