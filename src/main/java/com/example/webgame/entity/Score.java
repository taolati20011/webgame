package com.example.webgame.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer scoreId;
    public String gameName;
    public Integer playerId;
    public String playerDisplayName;
    public LocalDateTime scoreDate;
}
