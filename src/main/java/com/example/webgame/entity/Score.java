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
    public String gameName;
    public Integer score;
    public Integer playerId;
    public String playerDisplayName;
    public LocalDateTime scoreDate;
}
