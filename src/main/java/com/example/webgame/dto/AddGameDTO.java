package com.example.webgame.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddGameDTO {
    public String gameName;
    public String gameDescription;
    public LocalDateTime releaseDate;
    public String releaseLocation;
    public Long typeId;
}
